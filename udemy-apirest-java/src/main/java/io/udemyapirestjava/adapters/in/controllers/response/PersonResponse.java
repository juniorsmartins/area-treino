package io.udemyapirestjava.adapters.in.controllers.response;

public record PersonResponse(
        Long id,

        String firstName,

        String lastName,

        String address,

        String gender
) { }

