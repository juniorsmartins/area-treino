package com.desafiov2picpayjava.application.ports.out;

import com.desafiov2picpayjava.application.core.domain.Usuario;

import java.util.List;

public interface UsuarioBuscarTodosOutputPort {

    List<Usuario> buscarTodos();
}

