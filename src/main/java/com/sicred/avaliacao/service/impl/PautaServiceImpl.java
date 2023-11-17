package com.sicred.avaliacao.service.impl;

import com.sicred.avaliacao.dto.PautaDTO;
import com.sicred.avaliacao.mapper.PautaMapper;
import com.sicred.avaliacao.model.Pauta;
import com.sicred.avaliacao.repository.PautaRepository;
import com.sicred.avaliacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaServiceImpl implements PautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaServiceImpl(PautaRepository pautaRepository) {
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
}

