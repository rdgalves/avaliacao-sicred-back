package com.sicred.avaliacao.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.service.VotoConsumer;
import com.sicred.avaliacao.service.VotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class VotoConsumerImpl implements VotoConsumer {

    private final Logger logger = LoggerFactory.getLogger(VotoConsumerImpl.class);
    private final VotoService votoService;

    @Autowired
    public VotoConsumerImpl(VotoService votoService) {
        this.votoService = votoService;
    }

    @Override
    @KafkaListener(topics = "votos-topic", groupId = "voto-consumer-group")
    public void processarVoto(String votoJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            VotoRequestDTO votoRequestDTO = objectMapper.readValue(votoJson, VotoRequestDTO.class);
            votoService.processarVoto(votoRequestDTO);
        } catch (Exception e) {
            logger.error("Erro ao processar voto: ", e);
        }
    }
}
