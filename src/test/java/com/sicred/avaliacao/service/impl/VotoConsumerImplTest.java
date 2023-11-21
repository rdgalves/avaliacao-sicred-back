package com.sicred.avaliacao.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.enums.SimNaoEnum;
import com.sicred.avaliacao.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VotoConsumerImplTest {

    @Mock
    private VotoService votoService;

    @InjectMocks
    private VotoConsumerImpl votoConsumer;

    private String votoJson;
    private VotoRequestDTO votoRequestDTO;

    @BeforeEach
    void setUp() throws Exception {
        votoRequestDTO = new VotoRequestDTO();
        votoRequestDTO.setCpf("12345678900");
        votoRequestDTO.setEmail("teste@example.com");
        votoRequestDTO.setNome("Teste da Silva");
        votoRequestDTO.setSessaoId(1L);
        votoRequestDTO.setVoto(SimNaoEnum.SIM);
        ObjectMapper objectMapper = new ObjectMapper();
        votoJson = objectMapper.writeValueAsString(votoRequestDTO);
    }

    @Test
    void deveProcessarVoto() throws Exception {
        votoConsumer.processarVoto(votoJson);

        verify(votoService).processarVoto(any(VotoRequestDTO.class));
    }

    @Test
    void deveLancarExcecaoAoProcessarVotoComJsonInvalido() {
        String invalidJson = "invalid json";
        votoConsumer.processarVoto(invalidJson);
    }
}
