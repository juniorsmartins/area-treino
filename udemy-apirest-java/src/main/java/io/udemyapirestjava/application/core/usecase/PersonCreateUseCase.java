package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonCreateInputPort;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class PersonCreateUseCase implements PersonCreateInputPort {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonCreateUseCase.class.getName());

    @Override
    public Person create(Person person) {

        logger.info("Creating one person!");
        person.setId(counter.incrementAndGet());
        return person;
    }
}

