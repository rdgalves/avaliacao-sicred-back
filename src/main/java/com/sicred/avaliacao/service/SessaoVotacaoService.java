package com.sicred.avaliacao.service;

import com.sicred.avaliacao.dto.SessaoVotacaoRequestDTO;
import com.sicred.avaliacao.model.SessaoVotacao;

import java.util.List;

public interface SessaoVotacaoService {
    SessaoVotacao criarSessaoVotacao(SessaoVotacaoRequestDTO sessaoVotacaoRequestDTO);
    List<SessaoVotacao> listarTodasSessoes();
}
