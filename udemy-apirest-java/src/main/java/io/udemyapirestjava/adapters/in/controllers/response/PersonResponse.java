package io.udemyapirestjava.adapters.in.controllers.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // Definir não apresentar campos quando nulo
public class PersonResponse extends RepresentationModel<PersonResponse> {

        private Long key;

        private String firstName;

        private String lastName;

        private String gender;

        private String address;
}

