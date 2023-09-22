package io.udemyapirestjava.config.exception;

import java.io.Serial;

public final class FailedToSaveException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToSaveException(String message) {
        super(message);
    }
}

