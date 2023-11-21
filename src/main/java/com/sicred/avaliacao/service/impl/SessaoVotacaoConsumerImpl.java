package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.enums.StatusEnum;
import com.sicred.avaliacao.exception.SessaoVotacaoException;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.repository.SessaoVotacaoRepository;
import com.sicred.avaliacao.service.SessaoVotacaoConsumer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SessaoVotacaoConsumerImpl implements SessaoVotacaoConsumer {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    private final MessageSource messageSource;

    @Autowired
    public SessaoVotacaoConsumerImpl(SessaoVotacaoRepository sessaoVotacaoRepository, MessageSource messageSource) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
        this.messageSource = messageSource;
    }

    @Override
    @Transactional
    @KafkaListener(topics = "inicio-sessao-topic", groupId = "sessao-votacao-group")
    public void processarInicioSessao(String pautaId) {
        Long id = Long.parseLong(pautaId);
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new SessaoVotacaoException(messageSource, "sessao.nao_encontrada", new Object[] { pautaId }));

        sessaoVotacao.setStatus(StatusEnum.ABERTO);
        sessaoVotacaoRepository.save(sessaoVotacao);
    }

    @Override
    @Transactional
    @KafkaListener(topics = "fim-sessao-topic", groupId = "sessao-votacao-group")
    public void processarFimSessao(String pautaId) {
        Long id = Long.parseLong(pautaId);
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new SessaoVotacaoException(messageSource, "sessao.nao_encontrada", new Object[] { pautaId }));

        sessaoVotacao.setStatus(StatusEnum.FECHADO);
        sessaoVotacaoRepository.save(sessaoVotacao);
    }
}