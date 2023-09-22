package io.udemyapirestjava.application.ports.out;

import io.udemyapirestjava.application.core.domain.Person;

import java.util.Optional;

public interface PersonFindByIdOutputPort {

    Optional<Person> find(Long id);
}

