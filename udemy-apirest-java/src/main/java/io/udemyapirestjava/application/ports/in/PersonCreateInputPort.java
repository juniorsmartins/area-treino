package io.udemyapirestjava.application.ports.in;

import io.udemyapirestjava.application.core.domain.Person;

public interface PersonCreateInputPort {

    Person create(Person person);
}

