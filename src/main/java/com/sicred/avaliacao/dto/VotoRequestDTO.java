package com.sicred.avaliacao.dto;

import com.sicred.avaliacao.enums.SimNaoEnum;
import com.sicred.avaliacao.validation.CpfValido;
import com.sicred.avaliacao.validation.EmailValido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VotoRequestDTO {

    @NotNull(message = "A CPF deve ser informado.")
    @CpfValido
    private String cpf;

    @NotNull(message = "A Email deve ser informado.")
    @EmailValido
    private String email;

    @NotNull(message = "A Nome deve ser informado.")
    @NotBlank(message = "A Nome deve ser informado.")
    private String nome;

    @NotNull(message = "A Sessão deve ser informada.")
    private Long sessaoId;

    @NotNull(message = "O voto deve ser informada. (SIM ou NAO)")
    private SimNaoEnum voto;

}
