package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.in.UsuarioCadastrarInputPort;
import com.desafiov2picpayjava.application.ports.out.UsuarioSalvarOutputPort;

import java.util.NoSuchElementException;
import java.util.Optional;

public class UsuarioCadastrarUseCase implements UsuarioCadastrarInputPort {

    private final UsuarioSalvarOutputPort usuarioSalvarOutputPort;

    public UsuarioCadastrarUseCase(UsuarioSalvarOutputPort usuarioSalvarOutputPort) {
        this.usuarioSalvarOutputPort = usuarioSalvarOutputPort;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {

        return Optional.of(usuario)
            .map(this.usuarioSalvarOutputPort::salvar)
            .orElseThrow(NoSuchElementException::new);
    }
}

