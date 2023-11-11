package com.desafiov2picpayjava.application.ports.in;

import com.desafiov2picpayjava.application.core.domain.Usuario;

import java.util.List;

public interface UsuarioBuscarTodosInputPort {

    List<Usuario> buscarTodos();
}

