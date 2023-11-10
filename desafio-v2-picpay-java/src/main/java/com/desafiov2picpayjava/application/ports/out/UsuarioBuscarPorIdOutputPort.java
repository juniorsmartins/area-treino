package com.desafiov2picpayjava.application.ports.out;

import com.desafiov2picpayjava.application.core.domain.Usuario;

public interface UsuarioBuscarPorIdOutputPort {

    Usuario buscarPorId(Long id);
}

