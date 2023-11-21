package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.enums.StatusEnum;
import com.sicred.avaliacao.exception.SessaoVotacaoException;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.repository.SessaoVotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class SessaoVotacaoConsumerImplTest {

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private SessaoVotacaoConsumerImpl sessaoVotacaoConsumer;

    private SessaoVotacao sessaoVotacao;

    @BeforeEach
    void setUp() {
        sessaoVotacao = new SessaoVotacao();
        sessaoVotacao.setSessaoId(1L);
        sessaoVotacao.setStatus(StatusEnum.ABERTO);
    }

    @Test
    void quandoProcessarInicioSessao_entaoAtualizarStatusParaAberto() {
        when(sessaoVotacaoRepository.findById(anyLong())).thenReturn(Optional.of(sessaoVotacao));

        sessaoVotacaoConsumer.processarInicioSessao("1");

        verify(sessaoVotacaoRepository).save(sessaoVotacao);
        assertEquals(StatusEnum.ABERTO, sessaoVotacao.getStatus());
    }

    @Test
    void quandoProcessarInicioSessaoComIdInexistente_entaoLancarExcecao() {
        when(sessaoVotacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SessaoVotacaoException.class, () -> sessaoVotacaoConsumer.processarInicioSessao("1"));
    }

    @Test
    void quandoProcessarFimSessao_entaoAtualizarStatusParaFechado() {
        when(sessaoVotacaoRepository.findById(anyLong())).thenReturn(Optional.of(sessaoVotacao));

        sessaoVotacaoConsumer.processarFimSessao("1");

        verify(sessaoVotacaoRepository).save(sessaoVotacao);
        assertEquals(StatusEnum.FECHADO, sessaoVotacao.getStatus());
    }

    @Test
    void quandoProcessarFimSessaoComIdInexistente_entaoLancarExcecao() {
        when(sessaoVotacaoRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(SessaoVotacaoException.class, () -> sessaoVotacaoConsumer.processarFimSessao("1"));
    }
}
