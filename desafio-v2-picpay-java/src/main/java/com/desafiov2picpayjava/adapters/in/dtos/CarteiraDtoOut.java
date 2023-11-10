package com.desafiov2picpayjava.adapters.in.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CarteiraDtoOut(

    Long id,

    BigDecimal saldo
) { }

