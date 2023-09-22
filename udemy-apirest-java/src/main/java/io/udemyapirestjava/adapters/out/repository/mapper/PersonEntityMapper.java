package io.udemyapirestjava.adapters.out.repository.mapper;

import io.udemyapirestjava.adapters.out.repository.entity.PersonEntity;
import io.udemyapirestjava.application.core.domain.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonEntityMapper {

    PersonEntity toPersonEntity(Person person);

    Person toPerson(PersonEntity personEntity);
}

