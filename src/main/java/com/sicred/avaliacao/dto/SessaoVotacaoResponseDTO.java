package com.sicred.avaliacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessaoVotacaoResponseDTO {
    @JsonProperty("sessaoId")
    private Long sessaoId;

    @JsonProperty("pauta")
    private PautaDTO pauta;

    @JsonProperty("inicio")
    private LocalDateTime inicio;

    @JsonProperty("duracao")
    private Integer duracao;

    @JsonProperty("status")
    private String status;

    @JsonProperty("votos")
    private List<VotoDTO> votos;
}
