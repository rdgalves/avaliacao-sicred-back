package com.sicred.avaliacao.mapper;

import com.sicred.avaliacao.dto.PautaDTO;
import com.sicred.avaliacao.model.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PautaMapper {

    PautaMapper INSTANCE = Mappers.getMapper(PautaMapper.class);

    Pauta toEntity(PautaDTO pautaDTO);
}

