package io.udemyapirestjava.config.exception;

import java.io.Serial;

public final class FailedToUpdateException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public FailedToUpdateException(String message) {
        super(message);
    }
}

