package io.udemyapirestjava.adapters.out;

import io.udemyapirestjava.adapters.out.repository.PersonRepository;
import io.udemyapirestjava.application.ports.out.PersonDeleteOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDeleteAdapter implements PersonDeleteOutputPort {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void delete(final Long id) {

        this.personRepository.deleteById(id);
    }
}

