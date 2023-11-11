package com.desafiov2picpayjava.config.exceptions.http_404;

public final class CarteiraNaoEncontradaPorIdException extends RecursoNaoEncontradoException {

    public CarteiraNaoEncontradaPorIdException(Long id) {
        super(String.format("Não encontrada Carteira com ID: %s", id));
    }
}

