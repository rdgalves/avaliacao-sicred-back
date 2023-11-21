package com.sicred.avaliacao.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoProducerImplTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private SessaoVotacaoProducerImpl sessaoVotacaoProducer;

    @Test
    void quandoEnviarEventoInicioSessao_entaoDeveEnviarMensagemKafka() {
        String pautaId = "123";
        sessaoVotacaoProducer.enviarEventoInicioSessao(pautaId);

        verify(kafkaTemplate).send(eq("inicio-sessao-topic"), eq(pautaId));
    }

    @Test
    void quandoEnviarEventoFimSessao_entaoDeveEnviarMensagemKafka() {
        String pautaId = "456";
        sessaoVotacaoProducer.enviarEventoFimSessao(pautaId);

        verify(kafkaTemplate).send(eq("fim-sessao-topic"), eq(pautaId));
    }
}
