package io.udemyapirestjava.adapters.in.controllers.mapper;

import io.udemyapirestjava.adapters.in.controllers.response.PersonResponse;
import io.udemyapirestjava.application.core.domain.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonResponseMapper {

    PersonResponse toPersonResponse(Person person);
}

