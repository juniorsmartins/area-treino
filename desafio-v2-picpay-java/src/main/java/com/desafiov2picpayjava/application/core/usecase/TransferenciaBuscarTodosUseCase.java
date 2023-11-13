package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Transferencia;
import com.desafiov2picpayjava.application.ports.in.TransferenciaBuscarTodosInputPort;
import com.desafiov2picpayjava.application.ports.out.TransferenciaBuscarTodosOutputPort;

import java.util.List;
import java.util.logging.Logger;

public class TransferenciaBuscarTodosUseCase implements TransferenciaBuscarTodosInputPort {

    private final Logger logger = Logger.getLogger(TransferenciaBuscarTodosUseCase.class.getName());

    private final TransferenciaBuscarTodosOutputPort transferenciaBuscarTodosOutputPort;

    public TransferenciaBuscarTodosUseCase(TransferenciaBuscarTodosOutputPort transferenciaBuscarTodosOutputPort) {
        this.transferenciaBuscarTodosOutputPort = transferenciaBuscarTodosOutputPort;
    }

    @Override
    public List<Transferencia> buscarTodos() {

        this.logger.info("UseCase - iniciado processamento de requisição de buscar histórico de Transferências.");

        var transferencias = this.transferenciaBuscarTodosOutputPort.buscarTodos();

        this.logger.info("UseCase - concluído processamento de requisição de buscar histórico de transferências.");

        return transferencias;
    }
}

