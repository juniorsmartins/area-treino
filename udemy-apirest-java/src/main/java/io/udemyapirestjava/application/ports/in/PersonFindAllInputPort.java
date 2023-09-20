package io.udemyapirestjava.application.ports.in;

import io.udemyapirestjava.application.core.domain.Person;

import java.util.List;

public interface PersonFindAllInputPort {

    List<Person> find();
}

