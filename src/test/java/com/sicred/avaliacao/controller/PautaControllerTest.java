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
        // Preparação
        PautaDTO pautaDTO = new PautaDTO();
        Pauta pauta = new Pauta();
        pauta.setPautaId(1L);
        when(pautaService.criarPauta(pautaDTO)).thenReturn(pauta);

        // Ação
        ResponseEntity<Pauta> resposta = pautaController.criarPauta(pautaDTO);

        // Verificação
        assertNotNull(resposta);
        assertEquals(201, resposta.getStatusCodeValue());
        assertEquals(URI.create("/pautas/1"), resposta.getHeaders().getLocation());
        verify(pautaService).criarPauta(pautaDTO);
    }

    @Test
    void listarPautasTest() {
        // Preparação
        Pauta pauta1 = new Pauta();
        Pauta pauta2 = new Pauta();
        List<Pauta> listaPautas = Arrays.asList(pauta1, pauta2);
        when(pautaService.listarTodasPautas()).thenReturn(listaPautas);

        // Ação
        ResponseEntity<List<Pauta>> resposta = pautaController.listarPautas();

        // Verificação
        assertNotNull(resposta);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(2, resposta.getBody().size());
        verify(pautaService).listarTodasPautas();
    }
}
