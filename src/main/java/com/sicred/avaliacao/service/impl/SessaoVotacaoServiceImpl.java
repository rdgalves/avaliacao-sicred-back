package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.dto.SessaoVotacaoDTO;
import com.sicred.avaliacao.dto.SessaoVotacaoRequestDTO;
import com.sicred.avaliacao.enums.StatusEnum;
import com.sicred.avaliacao.exception.PautaException;
import com.sicred.avaliacao.exception.SessaoVotacaoException;
import com.sicred.avaliacao.mapper.SessaoVotacaoMapper;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.repository.SessaoVotacaoRepository;
import com.sicred.avaliacao.service.PautaService;
import com.sicred.avaliacao.service.SessaoVotacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessaoVotacaoServiceImpl implements SessaoVotacaoService {

    private final Logger logger = LoggerFactory.getLogger(SessaoVotacaoService.class);


    private final MessageSource messageSource;
    private final SessaoVotacaoRepository sessaoVotacaoRepository;
    private final PautaService pautaService;

    private final SessaoVotacaoMapper sessaoVotacaoMapper;

    @Autowired
    public SessaoVotacaoServiceImpl(MessageSource messageSource, SessaoVotacaoRepository sessaoVotacaoRepository, @Lazy PautaService pautaService, SessaoVotacaoMapper sessaoVotacaoMapper) {
        this.messageSource = messageSource;
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.pautaService = pautaService;
        this.sessaoVotacaoMapper = sessaoVotacaoMapper;
    }



    @Override
    public SessaoVotacao criarSessaoVotacao(SessaoVotacaoRequestDTO sessaoVotacaoDTO) {
        this.verificaSeExistePautaInformada(sessaoVotacaoDTO);
        this.verificarSeExisteSessaoParaPauta(sessaoVotacaoDTO);
        return sessaoVotacaoRepository.save(sessaoVotacaoMapper.toEntity(sessaoVotacaoDTO));
    }

    @Override
    public List<SessaoVotacaoDTO> listarTodasSessoes() {
        List<SessaoVotacao> sessoes = sessaoVotacaoRepository.findAll();
        return sessoes.stream()
                .map(sessaoVotacaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Verifica se a pauta informada existe.
     *
     * @param sessaoVotacaoDTO DTO contendo o ID da pauta a ser verificada.
     * @throws PautaException se a pauta não existir.
     */
    private void verificaSeExistePautaInformada(SessaoVotacaoRequestDTO sessaoVotacaoDTO) {
        this.pautaService.buscarPautaPorId(sessaoVotacaoDTO.getPautaId());
    }

    /**
     * Verifica se já existe uma sessão de votação para a pauta informada.
     * @param sessaoVotacaoDTO DTO da sessão de votação contendo o ID da pauta.
     * @throws PautaException se uma sessão para a pauta já está cadastrada.
     */
    private void verificarSeExisteSessaoParaPauta(SessaoVotacaoRequestDTO sessaoVotacaoDTO) {
        Long pautaId = sessaoVotacaoDTO.getPautaId();
        boolean sessaoExiste = sessaoVotacaoRepository.existsByPauta_PautaId(pautaId);
        if (sessaoExiste) {
            logger.error(messageSource.getMessage("log.sessao.pauta.cadastrada",
                    new Object[] { pautaId },
                    LocaleContextHolder.getLocale()));
            throw new SessaoVotacaoException(messageSource, "sessao.pauta.cadastrada");
        }
    }

    public List<SessaoVotacao> encontrarSessoesParaAbrir() {
        LocalDateTime agora = LocalDateTime.now();
        return sessaoVotacaoRepository.findByInicioLessThanEqualAndStatus(agora, StatusEnum.PENDENTE);
    }

    public List<SessaoVotacao> encontrarSessoesParaFechar() {
        LocalDateTime agora = LocalDateTime.now();
        List<SessaoVotacao> sessoesParaFechar = new ArrayList<>();

        List<SessaoVotacao> todasSessoes = sessaoVotacaoRepository.findByStatus(StatusEnum.ABERTO);
        for (SessaoVotacao sessao : todasSessoes) {
            //Sessão estipulada em minutos para maior praticidade na realização dos testes.
            LocalDateTime fimSessao = sessao.getInicio().plusMinutes(sessao.getDuracao());
            if (fimSessao.isBefore(agora)) {
                sessoesParaFechar.add(sessao);
            }
        }

        return sessoesParaFechar;
    }
}
