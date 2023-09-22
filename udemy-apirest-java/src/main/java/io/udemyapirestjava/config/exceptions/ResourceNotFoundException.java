package io.udemyapirestjava.config.exceptions;

import java.io.Serial;

public final class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

