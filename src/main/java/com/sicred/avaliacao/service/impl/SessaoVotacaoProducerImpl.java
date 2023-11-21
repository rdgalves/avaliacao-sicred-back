package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.service.SessaoVotacaoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SessaoVotacaoProducerImpl implements SessaoVotacaoProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public SessaoVotacaoProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void enviarEventoInicioSessao(String pautaId) {
        kafkaTemplate.send("inicio-sessao-topic", pautaId);
    }

    @Override
    public void enviarEventoFimSessao(String pautaId) {
        kafkaTemplate.send("fim-sessao-topic", pautaId);
    }
}