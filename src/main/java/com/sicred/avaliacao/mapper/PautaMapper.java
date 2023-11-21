package com.sicred.avaliacao.mapper;

import com.sicred.avaliacao.dto.PautaDTO;
import com.sicred.avaliacao.dto.SessaoVotacaoDTO;
import com.sicred.avaliacao.model.Pauta;
import com.sicred.avaliacao.model.SessaoVotacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    Pauta toEntity(PautaDTO pautaDTO);

    default PautaDTO toDto(Pauta pauta) {
        if (pauta == null) {
            return null;
        }

        return PautaDTO.builder()
                .pautaId(pauta.getPautaId())
                .titulo(pauta.getTitulo())
                .descricao(pauta.getDescricao())
                .sessoes(SessaoVotacaoMapper.INSTANCE.toSessaoVotacaoDTOList(pauta.getSessoes()))
                .build();
    }

    @Named("toDtoWithoutSessoes")
    default PautaDTO toDtoWithoutSessoes(Pauta pauta) {
        if ( pauta == null ) {
            return null;
        }
        return PautaDTO.builder()
                .pautaId(pauta.getPautaId())
                .titulo(pauta.getTitulo())
                .descricao(pauta.getDescricao())
                .build();
    }

}


