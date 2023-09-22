package io.udemyapirestjava.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class UnsupportedMathOperationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UnsupportedMathOperationException(String mensagem) {
        super(mensagem);
    }
}

