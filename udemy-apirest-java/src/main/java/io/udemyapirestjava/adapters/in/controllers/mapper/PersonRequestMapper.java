package io.udemyapirestjava.adapters.in.controllers.mapper;

import io.udemyapirestjava.adapters.in.controllers.request.PersonRequest;
import io.udemyapirestjava.application.core.domain.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonRequestMapper {

    Person toPerson(PersonRequest personRequest);
}

