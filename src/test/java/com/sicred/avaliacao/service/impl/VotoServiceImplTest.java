package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.dto.ResultadoVotacaoDTO;
import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.enums.SimNaoEnum;
import com.sicred.avaliacao.enums.StatusEnum;
import com.sicred.avaliacao.mapper.AssociadoMapper;
import com.sicred.avaliacao.mapper.VotoMapper;
import com.sicred.avaliacao.model.Associado;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.model.Voto;
import com.sicred.avaliacao.repository.VotoRepository;
import com.sicred.avaliacao.service.AssociadoService;
import com.sicred.avaliacao.service.SessaoVotacaoService;
import com.sicred.avaliacao.service.VotoProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class VotoServiceImplTest {

    @Mock
    private MessageSource messageSource;
    @Mock
    private AssociadoService associadoService;
    @Mock
    private VotoRepository votoRepository;
    @Mock
    private AssociadoMapper associadoMapper;
    @Mock
    private VotoMapper votoMapper;
    @Mock
    private SessaoVotacaoService sessaoVotacaoService;
    @Mock
    private VotoProducer votoProducer;

    @InjectMocks
    private VotoServiceImpl votoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void registrarVoto_DeveEnviarVotoParaProcessamento() {
        VotoRequestDTO votoRequestDTO = new VotoRequestDTO();
        votoRequestDTO.setCpf("12345678900");
        votoRequestDTO.setEmail("teste@example.com");
        votoRequestDTO.setNome("Teste da Silva");
        votoRequestDTO.setSessaoId(1L);
        votoRequestDTO.setVoto(SimNaoEnum.NAO);
        votoService.registrarVoto(votoRequestDTO);

        verify(votoProducer).enviarVotoParaProcessamento(votoRequestDTO);
    }

    @Test
    void processarVoto_DeveSalvarVotoQuandoValido() {
        VotoRequestDTO votoRequestDTO = new VotoRequestDTO();
        votoRequestDTO.setCpf("12345678900");
        votoRequestDTO.setEmail("teste@example.com");
        votoRequestDTO.setNome("Teste da Silva");
        votoRequestDTO.setSessaoId(1L);
        votoRequestDTO.setVoto(SimNaoEnum.NAO);

        Associado associado = new Associado();
        associado.setCpf("12345678900");
        associado.setEmail("teste@example.com");
        associado.setNome("Teste da Silva");

        when(associadoService.buscarAssociadoPorCPF(votoRequestDTO.getCpf())).thenReturn(Optional.of(associado));
        when(sessaoVotacaoService.buscarSessaoPorId(votoRequestDTO.getSessaoId())).thenReturn(new SessaoVotacao());
        when(votoRepository.existsByAssociadoAndSessaoSessaoId(associado, votoRequestDTO.getSessaoId())).thenReturn(false);

        votoService.processarVoto(votoRequestDTO);

        verify(votoRepository).save(any(Voto.class));
    }



    @Test
    void contabilizarVotos_DeveRetornarResultadoDaVotacao() {
        long pautaId = 1L;
        List<Voto> votos = new ArrayList<>();

        when(votoRepository.findBySessaoPautaPautaId(pautaId)).thenReturn(votos);

        ResultadoVotacaoDTO resultado = votoService.contabilizarVotos(pautaId);

        assertNotNull(resultado);
    }
}
