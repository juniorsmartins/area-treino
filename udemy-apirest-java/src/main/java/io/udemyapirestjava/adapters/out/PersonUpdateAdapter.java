package io.udemyapirestjava.adapters.out;

import io.udemyapirestjava.adapters.out.repository.PersonRepository;
import io.udemyapirestjava.adapters.out.repository.mapper.PersonEntityMapper;
import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.out.PersonUpdateOutputPort;
import io.udemyapirestjava.config.exception.FailedToUpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonUpdateAdapter implements PersonUpdateOutputPort {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonEntityMapper personEntityMapper;

    @Override
    public Person update(Person person) {

        return Optional.of(person)
                .map(this.personEntityMapper::toPersonEntity)
                .map(this.personRepository::save)
                .map(this.personEntityMapper::toPerson)
                .orElseThrow(() -> new FailedToUpdateException("Failed when trying to update person in the database."));
    }
}

