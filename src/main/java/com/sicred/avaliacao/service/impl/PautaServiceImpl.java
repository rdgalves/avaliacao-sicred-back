package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.dto.PautaDTO;
import com.sicred.avaliacao.exception.PautaException;
import com.sicred.avaliacao.mapper.PautaMapper;
import com.sicred.avaliacao.model.Pauta;
import com.sicred.avaliacao.repository.PautaRepository;
import com.sicred.avaliacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaServiceImpl implements PautaService {
    private final MessageSource messageSource;
    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(MessageSource messageSource, PautaRepository pautaRepository) {
        this.messageSource = messageSource;
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta criarPauta(PautaDTO pautaDTO) {
        return pautaRepository.save(PautaMapper.INSTANCE.toEntity(pautaDTO));
    }

    @Override
    public List<Pauta> listarTodasPautas() {
        return pautaRepository.findAll();
    }

    @Override
    public Pauta buscarPautaPorId(Long id) {
        return this.pautaRepository.findById(id)
                .orElseThrow(() -> new PautaException(messageSource, "pauta.nao_encontrada"));
    }
}

