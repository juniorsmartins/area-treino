package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonUpdateInputPort;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class PersonUpdateUseCase implements PersonUpdateInputPort {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonUpdateUseCase.class.getName());

    @Override
    public Person update(Person person) {

        logger.info("Updating one person!");
        return person;
    }
}

