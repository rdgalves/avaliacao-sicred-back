package com.sicred.avaliacao.service;

import com.sicred.avaliacao.dto.VotoRequestDTO;

public interface VotoProducer {
    void enviarVotoParaProcessamento(VotoRequestDTO votoRequestDTO);
}
