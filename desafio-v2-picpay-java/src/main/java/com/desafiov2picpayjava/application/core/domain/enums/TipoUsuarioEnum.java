package com.desafiov2picpayjava.application.core.domain.enums;

import lombok.Getter;

@Getter
public enum TipoUsuarioEnum {

    COMUM("Comum"),
    LOJISTA("Lojista");

    private final String tipo;

    TipoUsuarioEnum(String tipo) {
        this.tipo = tipo;
    }
}

