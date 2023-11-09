package com.desafiov2picpayjava.adapters.in.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioDtoOut(

    Long id,

    String nome,

    String documento,

    String email,

    String senha,

    String tipo
) { }

