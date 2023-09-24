package io.udemyapirestjava.adapters.in.controllers.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "FirstName", "LastName", "Gender", "Address"}) // Definir ordem de serialização/deserialização
@JsonInclude(JsonInclude.Include.NON_NULL) // Definir não apresentar campos quando nulo
public record PersonResponse(
        @JsonProperty("ID")
        Long id,

        @JsonProperty("FirstName")
        String firstName,

        @JsonProperty("LastName")
        String lastName,

        @JsonProperty("Gender")
        String gender,

        @JsonProperty("Address")
        String address
) { }

