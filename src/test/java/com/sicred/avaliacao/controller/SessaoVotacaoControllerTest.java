package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.SessaoVotacaoDTO;
import com.sicred.avaliacao.dto.SessaoVotacaoRequestDTO;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.service.SessaoVotacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoControllerTest {

    @Mock
    private SessaoVotacaoService sessaoVotacaoService;

    @InjectMocks
    private SessaoVotacaoController sessaoVotacaoController;

    private SessaoVotacaoRequestDTO requestDTO;
    private SessaoVotacao sessaoVotacao;
    private SessaoVotacaoDTO sessaoVotacaoDTO;

    @BeforeEach
    public void setUp() {
        requestDTO = new SessaoVotacaoRequestDTO();
        sessaoVotacao = new SessaoVotacao();
        sessaoVotacaoDTO = new SessaoVotacaoDTO();
    }

    @Test
    public void quandoAbrirSessaoVotacao_entaoRetornarCreated() {
        when(sessaoVotacaoService.criarSessaoVotacao(any(SessaoVotacaoRequestDTO.class))).thenReturn(sessaoVotacao);

        ResponseEntity<SessaoVotacao> response = sessaoVotacaoController.abrirSessaoVotacao(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sessaoVotacao, response.getBody());
    }

    @Test
    public void quandoListarSessoes_entaoRetornarOkComLista() {
        List<SessaoVotacaoDTO> sessaoVotacaoDTOList = new ArrayList<>();
        sessaoVotacaoDTOList.add(sessaoVotacaoDTO);
        when(sessaoVotacaoService.listarTodasSessoes()).thenReturn(sessaoVotacaoDTOList);

        ResponseEntity<List<SessaoVotacaoDTO>> response = sessaoVotacaoController.listarSessoes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sessaoVotacaoDTOList, response.getBody());
    }
}

