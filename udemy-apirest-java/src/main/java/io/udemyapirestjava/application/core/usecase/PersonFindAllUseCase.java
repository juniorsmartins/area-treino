package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonFindAllInputPort;
import io.udemyapirestjava.application.ports.out.PersonFindAllOutputPort;

import java.util.List;
import java.util.logging.Logger;

public class PersonFindAllUseCase implements PersonFindAllInputPort {

    private final Logger logger = Logger.getLogger(PersonFindAllUseCase.class.getName());

    private final PersonFindAllOutputPort personFindAllOutputPort;

    public PersonFindAllUseCase(PersonFindAllOutputPort personFindAllOutputPort) {
        this.personFindAllOutputPort = personFindAllOutputPort;
    }

    @Override
    public List<Person> find() {

        logger.info("Finding list person!");

        return this.personFindAllOutputPort.findAll();
    }
}

