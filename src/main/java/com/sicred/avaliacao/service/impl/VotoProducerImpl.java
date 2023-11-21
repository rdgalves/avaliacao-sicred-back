package com.sicred.avaliacao.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.service.VotoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class VotoProducerImpl  implements VotoProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public VotoProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarVotoParaProcessamento(VotoRequestDTO votoRequestDTO) {
        String mensagem = converteVotoRequestParaJson(votoRequestDTO);
        kafkaTemplate.send("votos-topic", mensagem);
    }

    private String converteVotoRequestParaJson(VotoRequestDTO votoRequestDTO) {
        try {
            return objectMapper.writeValueAsString(votoRequestDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter VotoRequestDTO para JSON", e);
        }
    }
}
