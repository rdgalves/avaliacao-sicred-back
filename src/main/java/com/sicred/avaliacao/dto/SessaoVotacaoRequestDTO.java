package com.sicred.avaliacao.dto;

import com.sicred.avaliacao.validation.DataInicioValida;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessaoVotacaoRequestDTO {

    @NotNull(message = "A Pauta deve ser informada.")
    private Long pautaId;

    @NotNull(message = "A data e hora de início devem ser informadas. Formato: YYYY-MM-DDTHH:MM:SS, Exemplo: 2023-11-17T10:00:00")
    @DataInicioValida
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime inicio;

    private Integer duracao;

}

