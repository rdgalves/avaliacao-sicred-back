package com.sicred.avaliacao.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoVotacaoDTO {
    private LocalDateTime inicio;
    private Integer duracao;
}

