package com.sicred.avaliacao.controller;

import com.sicred.avaliacao.dto.PautaDTO;
import com.sicred.avaliacao.model.Pauta;
import com.sicred.avaliacao.service.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PautaControllerTest {

    @Mock
    private PautaService pautaService;

    @InjectMocks
    private PautaController pautaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarPautaTest() {
        PautaDTO pautaDTO = new PautaDTO();
        Pauta pauta = new Pauta();
        pauta.setPautaId(1L);
        when(pautaService.criarPauta(pautaDTO)).thenReturn(pauta);

        ResponseEntity<Pauta> resposta = pautaController.criarPauta(pautaDTO);

        assertNotNull(resposta);
        assertEquals(201, resposta.getStatusCodeValue());
        assertEquals(URI.create("/pautas/1"), resposta.getHeaders().getLocation());
        verify(pautaService).criarPauta(pautaDTO);
    }

    @Test
    void listarPautasTest() {
        PautaDTO pauta1 = new PautaDTO();
        PautaDTO pauta2 = new PautaDTO();
        List<PautaDTO> listaPautas = Arrays.asList(pauta1, pauta2);
        when(pautaService.listarTodasPautas()).thenReturn(listaPautas);

        ResponseEntity<List<PautaDTO>> resposta = pautaController.listarPautas();

        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(2, resposta.getBody().size());
        verify(pautaService).listarTodasPautas();
    }
}
