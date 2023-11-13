package com.desafiov2picpayjava.adapters.out;

import com.desafiov2picpayjava.adapters.out.mappers.TransferenciaOrmMapper;
import com.desafiov2picpayjava.adapters.out.repositories.TransferenciaRepository;
import com.desafiov2picpayjava.application.core.domain.Transferencia;
import com.desafiov2picpayjava.application.ports.out.TransferenciaBuscarTodosOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class TransferenciaBuscarTodosAdapter implements TransferenciaBuscarTodosOutputPort {

    private final Logger logger = Logger.getLogger(TransferenciaBuscarTodosAdapter.class.getName());

    private final TransferenciaRepository transferenciaRepository;

    private final TransferenciaOrmMapper transferenciaOrmMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Transferencia> buscarTodos() {

        this.logger.info("Adapter - iniciado buscar histórico de Transferências no banco de dados.");

        var transferencias = this.transferenciaRepository.findAll()
            .stream()
            .map(this.transferenciaOrmMapper::toTransferencia)
            .toList();

        this.logger.info("Adapter - concluído buscar histórico de Transferências no banco de dados.");

        return transferencias;
    }
}

