package com.sicred.avaliacao.service;

public interface SessaoVotacaoConsumer {
    void processarInicioSessao(String pautaId);

    void processarFimSessao(String pautaId);
}
