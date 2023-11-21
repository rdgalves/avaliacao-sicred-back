package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.dto.ResultadoVotacaoDTO;
import com.sicred.avaliacao.dto.VotoDTO;
import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.enums.SimNaoEnum;
import com.sicred.avaliacao.enums.StatusEnum;
import com.sicred.avaliacao.exception.VotoException;
import com.sicred.avaliacao.mapper.AssociadoMapper;
import com.sicred.avaliacao.mapper.VotoMapper;
import com.sicred.avaliacao.model.Associado;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.model.Voto;
import com.sicred.avaliacao.repository.VotoRepository;
import com.sicred.avaliacao.service.AssociadoService;
import com.sicred.avaliacao.service.SessaoVotacaoService;
import com.sicred.avaliacao.service.VotoProducer;
import com.sicred.avaliacao.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoServiceImpl implements VotoService {
    private final Logger logger = LoggerFactory.getLogger(VotoServiceImpl.class);
    private final MessageSource messageSource;
    private final AssociadoService associadoService;
    private final VotoRepository votoRepository;
    private final AssociadoMapper associadoMapper;
    private final VotoMapper votoMapper;
    private final SessaoVotacaoService sessaoVotacaoService;

    private final VotoProducer votoProducer;

    @Autowired
    public VotoServiceImpl(MessageSource messageSource, AssociadoService associadoService, VotoRepository votoRepository, AssociadoMapper associadoMapper, VotoMapper votoMapper, SessaoVotacaoService sessaoVotacaoService, VotoProducer votoProducer) {
        this.messageSource = messageSource;
        this.associadoService = associadoService;
        this.votoRepository = votoRepository;
        this.associadoMapper = associadoMapper;
        this.votoMapper = votoMapper;
        this.sessaoVotacaoService = sessaoVotacaoService;
        this.votoProducer = votoProducer;
    }

    @Override
    public void registrarVoto(VotoRequestDTO votoRequestDTO) {
        votoProducer.enviarVotoParaProcessamento(votoRequestDTO);
    }

    @Override
    public void processarVoto(VotoRequestDTO votoRequestDTO) {
        Associado associado = obterOuCadastrarAssociado(votoRequestDTO);
        verificarSeJaVotou(votoRequestDTO, associado);
        salvarVoto(votoRequestDTO, associado);
    }


    private Associado obterOuCadastrarAssociado(VotoRequestDTO votoRequestDTO) {
        return associadoService.buscarAssociadoPorCPF(votoRequestDTO.getCpf())
                .orElseGet(() -> associadoService.cadastrarAssociado(associadoMapper.votoRequestDTOToAssociado(votoRequestDTO)));
    }

    private void verificarSeJaVotou(VotoRequestDTO votoRequestDTO, Associado associado) {
        if (votoRepository.existsByAssociadoAndSessaoSessaoId(associado, votoRequestDTO.getSessaoId())) {
            throw new VotoException(messageSource, "voto.ja_votou");
        }
    }

    private VotoDTO salvarVoto(VotoRequestDTO votoRequestDTO, Associado associado) {
        SessaoVotacao sessao = sessaoVotacaoService.buscarSessaoPorId(votoRequestDTO.getSessaoId());
        verificaStatusSessao(sessao);
        Voto voto = Voto.builder()
                .associado(associado)
                .sessao(sessao)
                .voto(votoRequestDTO.getVoto())
                .build();
        Voto votoSalvo = votoRepository.save(voto);
        return votoMapper.toDTO(votoSalvo);
    }

    private void verificaStatusSessao(SessaoVotacao sessao) {
        if (StatusEnum.PENDENTE.equals(sessao.getStatus())) {
            throw new VotoException(messageSource, "sessao.pendente");
        }

        if (StatusEnum.FECHADO.equals(sessao.getStatus())) {
            throw new VotoException(messageSource, "sessao.fechada");
        }
    }

    @Override
    public ResultadoVotacaoDTO contabilizarVotos(Long pautaId) {
        List<Voto> votosDaPauta = votoRepository.findBySessaoPautaPautaId(pautaId);

        long votosSim = votosDaPauta.stream()
                .filter(voto -> SimNaoEnum.SIM.equals(voto.getVoto()))
                .count();

        long votosNao = votosDaPauta.stream()
                .filter(voto -> SimNaoEnum.NAO.equals(voto.getVoto()))
                .count();

        return new ResultadoVotacaoDTO(pautaId, votosSim, votosNao);
    }
}
