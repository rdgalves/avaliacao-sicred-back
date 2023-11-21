package com.sicred.avaliacao.service;

import com.sicred.avaliacao.model.Associado;

import java.util.Optional;

public interface AssociadoService {
    void validarDadosDoAssociado(Associado associado);

    Associado cadastrarAssociado(Associado associado);

    Optional<Associado> buscarAssociadoPorCPF(String cpf);
}
