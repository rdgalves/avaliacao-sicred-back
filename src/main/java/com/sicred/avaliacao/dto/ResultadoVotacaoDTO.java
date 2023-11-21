package com.sicred.avaliacao.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoVotacaoDTO {
    private Long pautaId;
    private Long totalVotosSim;
    private Long totalVotosNao;
}

