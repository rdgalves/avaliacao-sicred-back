package com.sicred.avaliacao.service;

import com.sicred.avaliacao.dto.ResultadoVotacaoDTO;
import com.sicred.avaliacao.dto.VotoDTO;
import com.sicred.avaliacao.dto.VotoRequestDTO;

public interface VotoService {

    void registrarVoto(VotoRequestDTO votoRequestDTO);

    ResultadoVotacaoDTO contabilizarVotos(Long pautaId);

    void processarVoto(VotoRequestDTO votoRequestDTO);
}
