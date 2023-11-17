package com.sicred.avaliacao.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoVotacaoDTO {
    private Long sessaoId;
    private Long totalVotosSim;
    private Long totalVotosNao;
    private String resultado;
}

