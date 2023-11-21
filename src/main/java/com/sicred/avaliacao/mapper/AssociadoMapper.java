package com.sicred.avaliacao.mapper;

import com.sicred.avaliacao.dto.VotoRequestDTO;
import com.sicred.avaliacao.model.Associado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AssociadoMapper {

    AssociadoMapper INSTANCE = Mappers.getMapper(AssociadoMapper.class);

    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "nome", target = "nome")
    Associado votoRequestDTOToAssociado(VotoRequestDTO votoRequestDTO);
}
