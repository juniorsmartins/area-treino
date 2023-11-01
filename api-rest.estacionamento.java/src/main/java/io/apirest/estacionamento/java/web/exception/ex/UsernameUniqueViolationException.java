package io.apirest.estacionamento.java.web.exception.ex;

public final class UsernameUniqueViolationException extends RuntimeException {

    public UsernameUniqueViolationException(String mensagem) {
        super(mensagem);
    }
}

