package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.in.UsuarioBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.out.UsuarioBuscarPorIdOutputPort;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class UsuarioBuscarPorIdUseCase implements UsuarioBuscarPorIdInputPort {

    private final Logger logger = Logger.getLogger(UsuarioBuscarPorIdUseCase.class.getName());

    private final UsuarioBuscarPorIdOutputPort usuarioBuscarPorIdOutputPort;

    public UsuarioBuscarPorIdUseCase(UsuarioBuscarPorIdOutputPort usuarioBuscarPorIdOutputPort) {
        this.usuarioBuscarPorIdOutputPort = usuarioBuscarPorIdOutputPort;
    }

    @Override
    public Usuario buscarPorId(final Long id) {

        logger.info("UseCase - iniciado processamento de requisição de buscar Usuário por id.");

        var usuarioBuscado = Optional.of(id)
            .map(this.usuarioBuscarPorIdOutputPort::buscarPorId)
            .orElseThrow(NoSuchElementException::new);

        logger.info("UseCase - concluído processamento de requisição de buscar Usuário por id.");

        return usuarioBuscado;
    }
}

