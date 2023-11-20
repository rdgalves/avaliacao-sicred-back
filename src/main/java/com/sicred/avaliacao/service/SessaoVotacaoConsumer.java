package com.sicred.avaliacao.service;

import com.sicred.avaliacao.enums.StatusEnum;
import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.repository.SessaoVotacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SessaoVotacaoConsumer {

    private final SessaoVotacaoRepository sessaoVotacaoRepository;

    @Autowired
    public SessaoVotacaoConsumer(SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    @Transactional
    @KafkaListener(topics = "inicio-sessao-topic", groupId = "sessao-votacao-group")
    public void processarInicioSessao(String pautaId) {
        Long id = Long.parseLong(pautaId);
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Sessão de votação não encontrada para o ID: " + pautaId));

        sessaoVotacao.setStatus(StatusEnum.ABERTO);
        sessaoVotacaoRepository.save(sessaoVotacao);
    }

    @Transactional
    @KafkaListener(topics = "fim-sessao-topic", groupId = "sessao-votacao-group")
    public void processarFimSessao(String pautaId) {
        Long id = Long.parseLong(pautaId);
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Sessão de votação não encontrada para o ID: " + pautaId));

        sessaoVotacao.setStatus(StatusEnum.FECHADO);
        sessaoVotacaoRepository.save(sessaoVotacao);
    }

}