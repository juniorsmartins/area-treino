package io.udemyapirestjava.adapters.out;

import io.udemyapirestjava.adapters.out.repository.PersonRepository;
import io.udemyapirestjava.adapters.out.repository.mapper.PersonEntityMapper;
import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.out.PersonFindAllOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonFindAllAdapter implements PersonFindAllOutputPort {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonEntityMapper personEntityMapper;

    @Override
    public List<Person> findAll() {

        return this.personRepository.findAll()
                .stream()
                .map(this.personEntityMapper::toPerson)
                .toList();
    }
}

