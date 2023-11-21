package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.dto.SessaoVotacaoDTO;
import com.sicred.avaliacao.dto.SessaoVotacaoRequestDTO;
import com.sicred.avaliacao.mapper.SessaoVotacaoMapper;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.repository.SessaoVotacaoRepository;
import com.sicred.avaliacao.service.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessaoVotacaoServiceImplTest {

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @Mock
    private PautaService pautaService;
    @Mock
    private SessaoVotacaoMapper sessaoVotacaoMapper;
    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private SessaoVotacaoServiceImpl sessaoVotacaoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void criarSessaoVotacao_DeveSalvarSessaoQuandoValida() {
        // Dado
        SessaoVotacaoRequestDTO dto = new SessaoVotacaoRequestDTO();
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setSessaoId(1L); // Atribua um ID para simular a sessão criada

        // Quando o mapper for chamado, deve retornar uma sessão de votação
        when(sessaoVotacaoMapper.toEntity(any(SessaoVotacaoRequestDTO.class))).thenReturn(sessaoVotacao);

        // Quando o repositório salvar a sessão, deve retornar a mesma sessão
        when(sessaoVotacaoRepository.save(any(SessaoVotacao.class))).thenReturn(sessaoVotacao);

        // Ação
        SessaoVotacao resultado = sessaoVotacaoService.criarSessaoVotacao(dto);

        // Verificação
        assertThat(resultado).isNotNull();
        assertThat(resultado.getSessaoId()).isEqualTo(sessaoVotacao.getSessaoId());
        verify(sessaoVotacaoRepository).save(any(SessaoVotacao.class));
    }


    @Test
    void listarTodasSessoes_DeveRetornarListaDeSessoes() {
        List<SessaoVotacao> sessaoVotacaoList = Collections.singletonList(new SessaoVotacao());
        when(sessaoVotacaoRepository.findAll()).thenReturn(sessaoVotacaoList);
        when(sessaoVotacaoMapper.toDTO(any(SessaoVotacao.class))).thenReturn(new SessaoVotacaoDTO());

        List<SessaoVotacaoDTO> resultList = sessaoVotacaoService.listarTodasSessoes();

        assertThat(resultList).isNotEmpty();
        verify(sessaoVotacaoRepository).findAll();
        verify(sessaoVotacaoMapper, times(sessaoVotacaoList.size())).toDTO(any(SessaoVotacao.class));
    }
}
