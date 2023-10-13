package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.core.domain.Person;
import io.udemyapirestjava.application.ports.in.PersonCreateInputPort;
import io.udemyapirestjava.application.ports.out.PersonCreateOutputPort;
import io.udemyapirestjava.config.exception.RequiredObjectIsNullException;

import java.util.logging.Logger;

public class PersonCreateUseCase implements PersonCreateInputPort {

    private final Logger logger = Logger.getLogger(PersonCreateUseCase.class.getName());

    private final PersonCreateOutputPort personCreateOutputPort;

    public PersonCreateUseCase(PersonCreateOutputPort personCreateOutputPort) {
        this.personCreateOutputPort = personCreateOutputPort;
    }

    @Override
    public Person create(Person person) {

        logger.info("Creating one person!");

        if (person == null) throw new RequiredObjectIsNullException();

        return this.personCreateOutputPort.create(person);
    }
}

