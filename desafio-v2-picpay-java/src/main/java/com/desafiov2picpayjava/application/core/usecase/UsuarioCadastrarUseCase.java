package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.in.UsuarioCadastrarInputPort;
import com.desafiov2picpayjava.application.ports.out.UsuarioSalvarOutputPort;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class UsuarioCadastrarUseCase implements UsuarioCadastrarInputPort {

    private final Logger logger = Logger.getLogger(UsuarioCadastrarUseCase.class.getName());

    private final UsuarioSalvarOutputPort usuarioSalvarOutputPort;

    public UsuarioCadastrarUseCase(UsuarioSalvarOutputPort usuarioSalvarOutputPort) {
        this.usuarioSalvarOutputPort = usuarioSalvarOutputPort;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {

        logger.info("UseCase - iniciado processamento de requisição de cadastro.");

        var usuarioCadastrado = Optional.of(usuario)
            .map(this.usuarioSalvarOutputPort::salvar)
            .orElseThrow(NoSuchElementException::new);

        logger.info("UseCase - concluído processamento de requisição de cadastro.");

        return usuarioCadastrado;
    }
}

