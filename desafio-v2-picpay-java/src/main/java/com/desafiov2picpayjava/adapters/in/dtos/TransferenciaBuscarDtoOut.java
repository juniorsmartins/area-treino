package com.desafiov2picpayjava.adapters.in.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TransferenciaBuscarDtoOut(

    Long id,

    BigDecimal value,

    CarteiraBuscarDtoOut pagador,

    CarteiraBuscarDtoOut beneficiario,

    OffsetDateTime dataTimeTransacao
) { }

