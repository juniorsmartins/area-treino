package com.desafiov2picpayjava.adapters.in.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CarteiraCadastrarDtoIn(

    @NotNull
    @PositiveOrZero
    BigDecimal saldo,

    @NotNull
    @Valid
    UsuarioIdDto usuario
) { }

