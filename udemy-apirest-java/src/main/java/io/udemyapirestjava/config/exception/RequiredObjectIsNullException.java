package io.udemyapirestjava.config.exception;

import java.io.Serial;

public final class RequiredObjectIsNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RequiredObjectIsNullException(String message) {
        super(message);
    }

    public RequiredObjectIsNullException() {
        this("It is not allowed to persist a null object!");
    }
}

