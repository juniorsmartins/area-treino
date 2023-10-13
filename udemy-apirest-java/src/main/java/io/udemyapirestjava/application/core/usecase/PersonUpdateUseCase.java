package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonFindByIdInputPort;
import io.udemyapirestjava.application.ports.in.PersonUpdateInputPort;
import io.udemyapirestjava.application.ports.out.PersonUpdateOutputPort;
import io.udemyapirestjava.config.exception.RequiredObjectIsNullException;

import java.util.logging.Logger;

public class PersonUpdateUseCase implements PersonUpdateInputPort {

    private final Logger logger = Logger.getLogger(PersonUpdateUseCase.class.getName());

    private final PersonFindByIdInputPort personFindByIdInputPort;

    private final PersonUpdateOutputPort personUpdateOutputPort;

    public PersonUpdateUseCase(PersonFindByIdInputPort personFindByIdInputPort,
                               PersonUpdateOutputPort personUpdateOutputPort) {
        this.personFindByIdInputPort = personFindByIdInputPort;
        this.personUpdateOutputPort = personUpdateOutputPort;
    }

    @Override
    public Person update(Person person) {

        if (person == null) throw new RequiredObjectIsNullException();

        this.personFindByIdInputPort.find(person.getId());

        logger.info("Updating one person!");

        return this.personUpdateOutputPort.update(person);
    }
}

