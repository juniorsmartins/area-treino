package io.udemyapirestjava.adapters.in.controllers.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "address"})
@JsonInclude(JsonInclude.Include.NON_NULL) // Definir não apresentar campos quando nulo
public class PersonResponse extends RepresentationModel<PersonResponse> implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @JsonProperty("id")
        private Long key;

        private String firstName;

        private String lastName;

        private String gender;

        private String address;
}

