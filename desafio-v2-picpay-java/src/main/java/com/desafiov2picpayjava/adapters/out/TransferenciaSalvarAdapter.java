package com.desafiov2picpayjava.adapters.out;

import com.desafiov2picpayjava.adapters.out.mappers.TransferenciaOrmMapper;
import com.desafiov2picpayjava.adapters.out.repositories.TransferenciaRepository;
import com.desafiov2picpayjava.application.core.domain.Transferencia;
import com.desafiov2picpayjava.application.ports.out.TransferenciaSalvarOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class TransferenciaSalvarAdapter implements TransferenciaSalvarOutputPort {

    private final Logger logger = Logger.getLogger(TransferenciaSalvarAdapter.class.getName());

    private final TransferenciaRepository transferenciaRepository;

    private final TransferenciaOrmMapper transferenciaOrmMapper;

    @Transactional
    @Override
    public Transferencia salvar(Transferencia transferencia) {

        this.logger.info("Adapter - iniciada persistência de transferência de valor entre Carteiras.");

        var transferido = Optional.of(transferencia)
            .map(this.transferenciaOrmMapper::toTransferenciaOrm)
            .map(this.transferenciaRepository::save)
            .map(this.transferenciaOrmMapper::toTransferencia)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Adapter - concluída persistência de transferência de valor entre Carteiras.");

        return transferido;
    }
}

