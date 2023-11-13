package com.desafiov2picpayjava.application.ports.out;

import com.desafiov2picpayjava.application.core.domain.Transferencia;

import java.util.List;

public interface TransferenciaBuscarTodosOutputPort {

    List<Transferencia> buscarTodos();
}

