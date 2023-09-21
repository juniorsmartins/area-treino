package io.udemyapirestjava.application.ports.in;

import io.udemyapirestjava.application.core.domain.Person;

public interface PersonUpdateInputPort {

    Person update(Person person);
}

