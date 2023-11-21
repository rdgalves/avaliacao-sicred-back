package com.sicred.avaliacao.mapper;

import com.sicred.avaliacao.dto.VotoDTO;
import com.sicred.avaliacao.model.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VotoMapper {

    Voto toEntity(VotoDTO votoDto);

    VotoDTO toDTO(Voto votoSalvo);
}
