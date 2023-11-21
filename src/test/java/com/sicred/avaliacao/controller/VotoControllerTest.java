package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.ResultadoVotacaoDTO;
import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.service.VotoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;


@ExtendWith(MockitoExtension.class)
class VotoControllerTest {

    @Mock
    private VotoService votoService;

    @InjectMocks
    private VotoController votoController;

    @Test
    void registrarVoto_DeveRetornarStatusOk() {
        VotoRequestDTO votoRequestDTO = new VotoRequestDTO();

        ResponseEntity<?> response = votoController.registrarVoto(votoRequestDTO);

        assertEquals(OK, response.getStatusCode());
        verify(votoService).registrarVoto(votoRequestDTO);
    }

    @Test
    void obterResultadoVotacao_DeveRetornarResultado() {
        Long pautaId = 1L;
        ResultadoVotacaoDTO resultadoEsperado = new ResultadoVotacaoDTO();

        when(votoService.contabilizarVotos(pautaId)).thenReturn(resultadoEsperado);

        ResponseEntity<ResultadoVotacaoDTO> response = votoController.obterResultadoVotacao(pautaId);

        assertEquals(OK, response.getStatusCode());
        assertEquals(resultadoEsperado, response.getBody());
        verify(votoService).contabilizarVotos(pautaId);
    }
}
