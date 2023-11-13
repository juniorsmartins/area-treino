package com.desafiov2picpayjava.application.ports.in;

import com.desafiov2picpayjava.application.core.domain.Transferencia;

import java.util.List;

public interface TransferenciaBuscarTodosInputPort {

    List<Transferencia> buscarTodos();
}

