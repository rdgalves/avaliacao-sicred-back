package com.sicred.avaliacao.service;

import com.sicred.avaliacao.dto.PautaDTO;
import com.sicred.avaliacao.model.Pauta;

import java.util.List;

public interface PautaService {
    Pauta criarPauta(PautaDTO pautaDTO);
    List<PautaDTO> listarTodasPautas();

    Pauta buscarPautaPorId(Long id);
}
