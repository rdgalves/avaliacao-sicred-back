package com.sicred.avaliacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PautaDTO {

    private Long pautaId;

    @NotBlank(message = "O título não pode ser vazio")
    @Size(max = 255, message = "O título não pode ter mais que 255 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição não pode ser vazia")
    @Size(max = 1000, message = "A descrição não pode ter mais que 1000 caracteres")
    private String descricao;

    private List<SessaoVotacaoDTO> sessoes;

}
