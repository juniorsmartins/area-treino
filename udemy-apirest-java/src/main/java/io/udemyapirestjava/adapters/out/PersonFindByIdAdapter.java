package io.udemyapirestjava.adapters.out;

import io.udemyapirestjava.adapters.out.repository.PersonRepository;
import io.udemyapirestjava.adapters.out.repository.mapper.PersonEntityMapper;
import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.out.PersonFindByIdOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonFindByIdAdapter implements PersonFindByIdOutputPort {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonEntityMapper personEntityMapper;

    @Override
    public Optional<Person> find(final Long id) {
        return this.personRepository.findById(id)
                .map(this.personEntityMapper::toPerson);
    }
}

