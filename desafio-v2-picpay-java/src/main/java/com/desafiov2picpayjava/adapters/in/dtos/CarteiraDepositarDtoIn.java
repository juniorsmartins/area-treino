package com.desafiov2picpayjava.adapters.in.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CarteiraDepositarDtoIn(

    @NotNull
    @Positive
    Long id,

    @NotNull
    @Positive
    BigDecimal saldo
) { }

