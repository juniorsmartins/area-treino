package io.udemyapirestjava.application.core.usecase;

import com.github.javafaker.Faker;
import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonCreateInputPort;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class PersonCreateUseCase implements PersonCreateInputPort {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonFindAllUseCase.class.getName());

    private final Faker faker;

    public PersonCreateUseCase(Faker faker) {
        this.faker = faker;
    }

    @Override
    public Person create(Person person) {

        logger.info("Creating person!");
        person.setId(counter.incrementAndGet());
        return person;
    }
}

