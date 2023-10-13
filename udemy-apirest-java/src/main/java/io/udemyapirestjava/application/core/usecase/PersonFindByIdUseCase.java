package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonFindByIdInputPort;
import io.udemyapirestjava.application.ports.out.PersonFindByIdOutputPort;
import io.udemyapirestjava.config.exception.ResourceNotFoundException;

import java.util.logging.Logger;

public class PersonFindByIdUseCase implements PersonFindByIdInputPort {

    private final Logger logger = Logger.getLogger(PersonFindByIdUseCase.class.getName());

    private final PersonFindByIdOutputPort personFindByIdOutputPort;

    public PersonFindByIdUseCase(PersonFindByIdOutputPort personFindByIdOutputPort) {
        this.personFindByIdOutputPort = personFindByIdOutputPort;
    }

    @Override
    public Person find(final Long id) {

        logger.info("Finding one person!");

        return this.personFindByIdOutputPort.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }
}

