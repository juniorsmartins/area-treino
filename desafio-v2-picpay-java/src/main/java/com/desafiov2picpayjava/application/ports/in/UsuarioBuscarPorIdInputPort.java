package com.desafiov2picpayjava.application.ports.in;

import com.desafiov2picpayjava.application.core.domain.Usuario;

public interface UsuarioBuscarPorIdInputPort {

    Usuario buscarPorId(Long id);
}

