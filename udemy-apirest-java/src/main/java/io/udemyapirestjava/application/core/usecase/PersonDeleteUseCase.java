package io.udemyapirestjava.application.core.usecase;

import io.udemyapirestjava.application.ports.in.PersonDeleteInputPort;

import java.util.logging.Logger;

public class PersonDeleteUseCase implements PersonDeleteInputPort {

    private Logger logger = Logger.getLogger(PersonDeleteUseCase.class.getName());

    @Override
    public void delete(final Long id) {

        logger.info("Deleting one person!");
    }
}

