package io.apirest.estacionamento.java.web.exception.ex;

public class CpfUniqueViolationException extends RuntimeException {

    public CpfUniqueViolationException(String message) {
        super(message);
    }
}

