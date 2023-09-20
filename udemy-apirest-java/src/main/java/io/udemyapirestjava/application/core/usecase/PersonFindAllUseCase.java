package io.udemyapirestjava.application.core.usecase;

import com.github.javafaker.Faker;
import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonFindAllInputPort;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

public class PersonFindAllUseCase implements PersonFindAllInputPort {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonFindAllUseCase.class.getName());

    private final Faker faker;

    public PersonFindAllUseCase(Faker faker) {
        this.faker = faker;
    }

    @Override
    public List<Person> find() {

        logger.info("Finding list person!");
        return List.of(this.mockPerson(), this.mockPerson());
    }

    private Person mockPerson() {

        var person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName(faker.name().firstName());
        person.setLastName(faker.name().lastName());
        person.setAddress(faker.address().fullAddress());
        person.setGender(this.faker.demographic().sex());
        return person;
    }
}

