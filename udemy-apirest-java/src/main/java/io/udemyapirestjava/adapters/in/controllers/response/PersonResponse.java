package io.udemyapirestjava.adapters.in.controllers.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PersonResponse(
        Long id,

        String firstName,

        String lastName,

        String gender,

        String address
) { }

