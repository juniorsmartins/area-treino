package com.desafiov2picpayjava.adapters.in.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UsuarioDtoIn(

    @NotBlank
    @Size(max = 100)
    String nome,

    @NotBlank
    @Size(min = 11, max = 14)
    String documento,

    @NotBlank
    @Size(max = 100)
    @Email(regexp = "^[a-z0-9_+.]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    String email,

    @NotBlank
    @Size(min = 6, max = 30)
    String senha,

    @NotBlank
    String tipo
) { }

