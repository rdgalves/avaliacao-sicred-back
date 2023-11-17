package com.sicred.avaliacao.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VotoDTO {

    private Long associadoId;   // ID do associado que está votando
    private Long sessaoId;      // ID da sessão de votação na qual o voto está sendo feito
    private String voto;        // Opção de voto ('Sim' ou 'Não')
}
