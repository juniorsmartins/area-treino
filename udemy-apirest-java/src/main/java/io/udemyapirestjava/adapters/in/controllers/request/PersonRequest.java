package io.udemyapirestjava.adapters.in.controllers.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;

@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "address"})
public record PersonRequest(
        Long id,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String gender,

        @NotBlank
        String address
) { }

