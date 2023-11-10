package com.desafiov2picpayjava.application.ports.out;

import com.desafiov2picpayjava.application.core.domain.Usuario;

import java.util.Optional;

public interface UsuarioBuscarPorIdOutputPort {

    Optional<Usuario> buscarPorId(Long id);
}

