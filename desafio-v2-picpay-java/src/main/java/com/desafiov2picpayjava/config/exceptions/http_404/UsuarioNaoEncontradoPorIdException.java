package com.desafiov2picpayjava.config.exceptions.http_404;

import com.desafiov2picpayjava.application.core.domain.Usuario;

public final class UsuarioNaoEncontradoPorIdException extends RecursoNaoEncontradoException {

    public UsuarioNaoEncontradoPorIdException(Long id) {
        super(String.format("Não encontrado Usuário com ID: %s", id));
    }
}

