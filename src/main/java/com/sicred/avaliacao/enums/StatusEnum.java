package com.sicred.avaliacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    PENDENTE("Aendente"),
    ABERTO("Aberto"),
    FECHADO("Fechado");

    private String status;
}
