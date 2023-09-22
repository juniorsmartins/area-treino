package io.udemyapirestjava.application.ports.out;

import io.udemyapirestjava.application.core.domain.Person;

public interface PersonCreateOutputPort {

    Person create(Person person);
}

