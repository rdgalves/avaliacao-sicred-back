package com.sicred.avaliacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SessaoVotacaoProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void enviarEventoInicioSessao(String pautaId) {
        kafkaTemplate.send("inicio-sessao-topic", pautaId);
    }

    public void enviarEventoFimSessao(String pautaId) {
        kafkaTemplate.send("fim-sessao-topic", pautaId);
    }
}