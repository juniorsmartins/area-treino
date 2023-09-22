package io.udemyapirestjava.application.ports.out;

import io.udemyapirestjava.application.core.domain.Person;

import java.util.List;

public interface PersonFindAllOutputPort {

    List<Person> findAll();
}

