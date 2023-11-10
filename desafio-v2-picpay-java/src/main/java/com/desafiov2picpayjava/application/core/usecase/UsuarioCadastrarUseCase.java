package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.core.usecase.utils.Utils;
import com.desafiov2picpayjava.application.ports.in.CarteiraCadastrarInputPort;
import com.desafiov2picpayjava.application.ports.in.UsuarioCadastrarInputPort;
import com.desafiov2picpayjava.application.ports.out.UsuarioSalvarOutputPort;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class UsuarioCadastrarUseCase implements UsuarioCadastrarInputPort {

    private final Logger logger = Logger.getLogger(UsuarioCadastrarUseCase.class.getName());

    private final UsuarioSalvarOutputPort usuarioSalvarOutputPort;

    private final CarteiraCadastrarInputPort carteiraCadastrarInputPort;

    private final Utils utils;

    public UsuarioCadastrarUseCase(UsuarioSalvarOutputPort usuarioSalvarOutputPort, Utils utils,
                                   CarteiraCadastrarInputPort carteiraCadastrarInputPort) {
        this.usuarioSalvarOutputPort = usuarioSalvarOutputPort;
        this.utils = utils;
        this.carteiraCadastrarInputPort = carteiraCadastrarInputPort;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {

        logger.info("UseCase - iniciado processamento de requisição de cadastro.");

        var usuarioCadastrado = Optional.of(usuario)
            .map(this::capitalizarNome)
            .map(this.usuarioSalvarOutputPort::salvar)
            .map(this.carteiraCadastrarInputPort::cadastrar)
            .orElseThrow(NoSuchElementException::new);

        logger.info("UseCase - concluído processamento de requisição de cadastro.");

        return usuarioCadastrado;
    }

    private Usuario capitalizarNome(Usuario usuario) {
        var nomeCapitalizado = this.utils.capitalizarTexto(usuario.getNome());
        usuario.setNome(nomeCapitalizado);
        return usuario;
    }
}

