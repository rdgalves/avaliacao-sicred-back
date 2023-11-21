package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.exception.AssociadoException;
import com.sicred.avaliacao.model.Associado;
import com.sicred.avaliacao.repository.AssociadoRepository;
import com.sicred.avaliacao.service.AssociadoService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;
    private final MessageSource messageSource;

    public AssociadoServiceImpl(AssociadoRepository associadoRepository, MessageSource messageSource) {
        this.associadoRepository = associadoRepository;
        this.messageSource = messageSource;
    }

    @Override
    public Associado cadastrarAssociado(Associado associado) {
        return associadoRepository.save(associado);
    }

    @Override
    public void validarDadosDoAssociado(Associado associado) {
        Optional<Associado> associadoExistente = buscarAssociadoPorCPF(associado.getCpf());
        if (associadoExistente.isPresent()) {
            Associado existente = associadoExistente.get();
            boolean nomeDiferente = !existente.getNome().equals(associado.getNome());
            boolean emailDiferente = !existente.getEmail().equals(associado.getEmail());

            if (nomeDiferente || emailDiferente) {
                throw new AssociadoException(messageSource, "associado.dados_inconsistentes", new Object[] {associado.getCpf()});
            }
        }
    }

    @Override
    public Optional<Associado> buscarAssociadoPorCPF(String cpf) {
        return associadoRepository.findByCpf(cpf);
    }
}
