package io.udemyapirestjava.adapters.in.controllers.mapper;

import io.udemyapirestjava.adapters.in.controllers.response.PersonResponse;
import io.udemyapirestjava.application.core.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonResponseMapper {

    @Mapping(source = "id", target = "key")
    PersonResponse toPersonResponse(Person person);
}

