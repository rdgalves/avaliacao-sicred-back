package com.sicred.avaliacao.service;

public interface SessaoVotacaoProducer {
    void enviarEventoInicioSessao(String pautaId);

    void enviarEventoFimSessao(String pautaId);
}
