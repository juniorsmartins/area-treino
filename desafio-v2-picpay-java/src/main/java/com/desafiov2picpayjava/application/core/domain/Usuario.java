package com.desafiov2picpayjava.application.core.domain;

import com.desafiov2picpayjava.application.core.domain.enums.TipoUsuarioEnum;

public final class Usuario {

    private long version;

    private Long id;

    private String nome;

    private String documento;

    private String email;

    private String senha;

    private TipoUsuarioEnum tipo;

    private Carteira carteira;
}

