package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonFindByIdInputPort;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class PersonFindByIdFindByIdUseCase implements PersonFindByIdInputPort {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonFindByIdFindByIdUseCase.class.getName());

    @Override
    public Person find(Long id) {

        logger.info("Finding one person!");

        var person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Sam");
        person.setLastName("Newman");
        person.setAddress("Califórnia - EUA");
        person.setGender("Male");
        return person;
    }
}

