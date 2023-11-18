package com.sicred.avaliacao.mapper;

import com.sicred.avaliacao.dto.SessaoVotacaoRequestDTO;
import com.sicred.avaliacao.dto.SessaoVotacaoResponseDTO;
import com.sicred.avaliacao.model.SessaoVotacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SessaoVotacaoMapper {

    SessaoVotacaoMapper INSTANCE = Mappers.getMapper(SessaoVotacaoMapper.class);

    @Mapping(source = "pautaId", target = "pauta.pautaId")
    SessaoVotacao toEntity(SessaoVotacaoRequestDTO sessaoVotacaoDTO);

    @Mapping(target = "pauta", source = "pauta")
    @Mapping(target = "votos", ignore = true) // Ignora se não for manipular votos aqui
    SessaoVotacaoResponseDTO toDto(SessaoVotacao sessaoVotacao);
}
