package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonFindByIdInputPort;
import io.udemyapirestjava.application.ports.out.PersonFindByIdOutputPort;
import io.udemyapirestjava.config.exception.ResourceNotFoundException;

import java.util.logging.Logger;

public class PersonFindByIdFindByIdUseCase implements PersonFindByIdInputPort {

    private Logger logger = Logger.getLogger(PersonFindByIdFindByIdUseCase.class.getName());

    private final PersonFindByIdOutputPort personFindByIdOutputPort;

    public PersonFindByIdFindByIdUseCase(PersonFindByIdOutputPort personFindByIdOutputPort) {
        this.personFindByIdOutputPort = personFindByIdOutputPort;
    }

    @Override
    public Person find(final Long id) {

        logger.info("Finding one person!");

        return this.personFindByIdOutputPort.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found!"));
    }
}

