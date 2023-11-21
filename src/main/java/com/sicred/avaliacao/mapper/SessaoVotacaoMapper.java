package com.sicred.avaliacao.mapper;

import com.sicred.avaliacao.dto.SessaoVotacaoDTO;
import com.sicred.avaliacao.dto.SessaoVotacaoRequestDTO;
import com.sicred.avaliacao.model.SessaoVotacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {PautaMapper.class, VotoMapper.class})
public interface SessaoVotacaoMapper {

    SessaoVotacaoMapper INSTANCE = Mappers.getMapper(SessaoVotacaoMapper.class);
    @Mappings({
            @Mapping(target = "pauta.pautaId", source = "pautaId"),
    })
    SessaoVotacao toEntity(SessaoVotacaoRequestDTO sessaoVotacaoRequestDTO);

    @Mappings({
            @Mapping(target = "pauta", source = "pauta", qualifiedByName = "toDtoWithoutSessoes"),
            @Mapping(target = "votos", ignore = true)
    })
    SessaoVotacaoDTO toDTO(SessaoVotacao sessaoVotacao);

    @Mappings({
            @Mapping(target = "pauta", ignore = true),
            @Mapping(target = "votos", ignore = true)
    })
    SessaoVotacaoDTO toDTOSemPauta(SessaoVotacao sessaoVotacao);

    @Named("toSessaoVotacaoDTOList")
    default List<SessaoVotacaoDTO> toSessaoVotacaoDTOList(List<SessaoVotacao> sessaoVotacaoList) {
        if (sessaoVotacaoList == null) {
            return null;
        }
        return sessaoVotacaoList.stream()
                .map(this::toDTOSemPauta)
                .collect(Collectors.toList());
    }
}

