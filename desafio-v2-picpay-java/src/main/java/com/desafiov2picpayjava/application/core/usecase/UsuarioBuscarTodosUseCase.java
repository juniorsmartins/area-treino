package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.in.UsuarioBuscarTodosInputPort;
import com.desafiov2picpayjava.application.ports.out.UsuarioBuscarTodosOutputPort;

import java.util.List;
import java.util.logging.Logger;

public class UsuarioBuscarTodosUseCase implements UsuarioBuscarTodosInputPort {

    private final Logger logger = Logger.getLogger(UsuarioBuscarTodosUseCase.class.getName());

    private final UsuarioBuscarTodosOutputPort usuarioBuscarTodosOutputPort;

    public UsuarioBuscarTodosUseCase(UsuarioBuscarTodosOutputPort usuarioBuscarTodosOutputPort) {
        this.usuarioBuscarTodosOutputPort = usuarioBuscarTodosOutputPort;
    }

    @Override
    public List<Usuario> buscarTodos() {

        this.logger.info("UseCase - iniciado processamento de requisição de buscar todos os Usuários.");

        var usuarios = this.usuarioBuscarTodosOutputPort.buscarTodos();

        this.logger.info("UseCase - concluído processamento de requisição de buscar todos os Usuários.");

        return usuarios;
    }
}

