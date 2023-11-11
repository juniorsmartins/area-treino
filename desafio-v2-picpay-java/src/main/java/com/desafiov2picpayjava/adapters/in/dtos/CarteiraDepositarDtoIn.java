package com.desafiov2picpayjava.adapters.in.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CarteiraDepositarDtoIn(

    @NotNull
    @Positive
    BigDecimal saldo,

    @NotNull
    @Valid
    UsuarioIdDto usuario
) { }

