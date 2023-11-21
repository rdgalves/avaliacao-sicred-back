package com.sicred.avaliacao.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.enums.SimNaoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VotoProducerImplTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private VotoProducerImpl votoProducer;

    private VotoRequestDTO votoRequestDTO;
    private String votoRequestJson;

    @BeforeEach
    void setUp() throws Exception {
        votoRequestDTO = new VotoRequestDTO();
        votoRequestDTO.setCpf("12345678900");
        votoRequestDTO.setEmail("teste@example.com");
        votoRequestDTO.setNome("Teste da Silva");
        votoRequestDTO.setSessaoId(1L);
        votoRequestDTO.setVoto(SimNaoEnum.SIM);
        ObjectMapper objectMapper = new ObjectMapper();
        votoRequestJson = objectMapper.writeValueAsString(votoRequestDTO);
    }

    @Test
    void deveEnviarVotoParaProcessamento() {
        votoProducer.enviarVotoParaProcessamento(votoRequestDTO);

        verify(kafkaTemplate).send("votos-topic", votoRequestJson);
    }
}
