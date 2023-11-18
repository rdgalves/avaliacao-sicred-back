package com.sicred.avaliacao.mapper;

import com.sicred.avaliacao.dto.VotoDTO;
import com.sicred.avaliacao.model.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VotoMapper {

    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);

    VotoDTO toDto(Voto voto);
    Voto toEntity(VotoDTO votoDto);
}
