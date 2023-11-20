package com.sicred.avaliacao.scheduler;

import com.sicred.avaliacao.model.SessaoVotacao;
import com.sicred.avaliacao.service.SessaoVotacaoProducer;
import com.sicred.avaliacao.service.SessaoVotacaoService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessaoVotacaoScheduler {

    @Autowired
    private SessaoVotacaoProducer sessaoVotacaoProducer;

    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

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