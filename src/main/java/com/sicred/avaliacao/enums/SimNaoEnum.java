package com.sicred.avaliacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SimNaoEnum {
    SIM("Sim"),
    NAO("Não");

    private String descricao;
}
