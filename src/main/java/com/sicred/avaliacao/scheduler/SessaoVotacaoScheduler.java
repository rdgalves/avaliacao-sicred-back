package com.sicred.avaliacao.scheduler;

import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.service.SessaoVotacaoProducer;
import com.sicred.avaliacao.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoVotacaoScheduler {
    private final SessaoVotacaoProducer sessaoVotacaoProducer;

    private final SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    public SessaoVotacaoScheduler(SessaoVotacaoProducer sessaoVotacaoProducer, SessaoVotacaoService sessaoVotacaoService) {
        this.sessaoVotacaoProducer = sessaoVotacaoProducer;
        this.sessaoVotacaoService = sessaoVotacaoService;
    }

    @Scheduled(fixedRate = 59000)
    public void verificarESinalizarSessoesParaAbrirEFechar() {
        List<SessaoVotacao> sessoesParaAbrir = sessaoVotacaoService.encontrarSessoesParaAbrir();
        for (SessaoVotacao sessao : sessoesParaAbrir) {
            sessaoVotacaoProducer.enviarEventoInicioSessao(sessao.getSessaoId().toString());
        }

        List<SessaoVotacao> sessoesParaFechar = sessaoVotacaoService.encontrarSessoesParaFechar();
        for (SessaoVotacao sessao : sessoesParaFechar) {
            sessaoVotacaoProducer.enviarEventoFimSessao(sessao.getSessaoId().toString());
        }
    }
}