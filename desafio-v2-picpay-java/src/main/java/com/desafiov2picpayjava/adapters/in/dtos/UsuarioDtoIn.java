package com.desafiov2picpayjava.adapters.in.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UsuarioDtoIn(

    @NotBlank
    @Size(max = 150)
    String nome,

    @NotBlank
    @Size(min = 11, max = 14)
    String documento,

    @NotBlank
    @Email
    @Size(max = 150)
    String email,

    @NotBlank
    @Size(max = 50)
    String senha,

    @NotBlank
    String tipo
) { }

