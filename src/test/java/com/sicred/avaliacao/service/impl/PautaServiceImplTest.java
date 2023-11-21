package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.dto.PautaDTO;
import com.sicred.avaliacao.exception.PautaException;
import com.sicred.avaliacao.mapper.PautaMapper;
import com.sicred.avaliacao.model.Pauta;
import com.sicred.avaliacao.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PautaServiceImplTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @InjectMocks
    private PautaServiceImpl pautaService;

    private PautaDTO pautaDTO;
    private Pauta pauta;

    @BeforeEach
    void setUp() {
        pautaDTO = new PautaDTO();
        pauta = new Pauta();
    }

    @Test
    void quandoCriarPauta_entaoRetornarPautaSalva() {
        when(pautaMapper.toEntity(any(PautaDTO.class))).thenReturn(pauta);
        when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);

        Pauta result = pautaService.criarPauta(pautaDTO);

        assertNotNull(result);
        verify(pautaRepository).save(pauta);
    }

    @Test
    void quandoListarTodasPautas_entaoRetornarListaDePautaDTO() {
        when(pautaRepository.findAll()).thenReturn(Arrays.asList(pauta));
        when(pautaMapper.toDto(any(Pauta.class))).thenReturn(pautaDTO);

        List<PautaDTO> result = pautaService.listarTodasPautas();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(pautaRepository).findAll();
    }

    @Test
    void quandoBuscarPautaPorId_entaoRetornarPauta() {
        Long id = 1L;
        when(pautaRepository.findById(id)).thenReturn(Optional.of(pauta));

        Pauta result = pautaService.buscarPautaPorId(id);

        assertNotNull(result);
        assertEquals(pauta, result);
    }

    @Test
    void quandoBuscarPautaPorIdInexistente_entaoLancarExcecao() {
        Long id = 1L;
        when(pautaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PautaException.class, () -> pautaService.buscarPautaPorId(id));
    }
}
