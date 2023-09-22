package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.ports.in.PersonDeleteInputPort;
import io.udemyapirestjava.application.ports.in.PersonFindByIdInputPort;
import io.udemyapirestjava.application.ports.out.PersonDeleteOutputPort;

import java.util.logging.Logger;

public class PersonDeleteUseCase implements PersonDeleteInputPort {

    private final Logger logger = Logger.getLogger(PersonDeleteUseCase.class.getName());

    private final PersonFindByIdInputPort personFindByIdInputPort;

    private final PersonDeleteOutputPort personDeleteOutputPort;

    public PersonDeleteUseCase(PersonFindByIdInputPort personFindByIdInputPort,
                               PersonDeleteOutputPort personDeleteOutputPort) {
        this.personFindByIdInputPort = personFindByIdInputPort;
        this.personDeleteOutputPort = personDeleteOutputPort;
    }

    @Override
    public void delete(final Long id) {

        logger.info("Deleting one person!");

        this.personFindByIdInputPort.find(id);
        this.personDeleteOutputPort.delete(id);
    }
}

