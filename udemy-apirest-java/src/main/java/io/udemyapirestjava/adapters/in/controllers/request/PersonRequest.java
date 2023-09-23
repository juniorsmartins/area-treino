package io.udemyapirestjava.adapters.in.controllers.request;

import jakarta.validation.constraints.NotBlank;

public record PersonRequest(
        Long id,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String address,

        @NotBlank
        String gender
) { }

