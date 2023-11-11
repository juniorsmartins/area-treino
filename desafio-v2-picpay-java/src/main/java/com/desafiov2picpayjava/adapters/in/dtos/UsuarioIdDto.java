package com.desafiov2picpayjava.adapters.in.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record UsuarioIdDto(

    @NotNull
    @Positive
    Long id
) { }

