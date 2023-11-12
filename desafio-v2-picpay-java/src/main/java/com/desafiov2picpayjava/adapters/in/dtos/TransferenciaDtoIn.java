package com.desafiov2picpayjava.adapters.in.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransferenciaDtoIn(

    @NotNull
    @Positive
    BigDecimal value,

    @NotNull
    @Positive
    Long payer,

    @NotNull
    @Positive
    Long payee
) { }

