package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Transferencia;
import com.desafiov2picpayjava.application.ports.in.CarteiraBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.in.TransferenciaEfetuarInputPort;
import com.desafiov2picpayjava.application.ports.out.TransferenciaSalvarOutputPort;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class TransferenciaEfetuarUseCase implements TransferenciaEfetuarInputPort {

    private final Logger logger = Logger.getLogger(TransferenciaEfetuarUseCase.class.getName());

    private final TransferenciaSalvarOutputPort transferenciaSalvarOutputPort;

    private final CarteiraBuscarPorIdInputPort carteiraBuscarPorIdInputPort;

    public TransferenciaEfetuarUseCase(TransferenciaSalvarOutputPort transferenciaSalvarOutputPort,
                                       CarteiraBuscarPorIdInputPort carteiraBuscarPorIdInputPort) {
        this.transferenciaSalvarOutputPort = transferenciaSalvarOutputPort;
        this.carteiraBuscarPorIdInputPort = carteiraBuscarPorIdInputPort;
    }

    @Override
    public void transferir(Transferencia transferencia) {

        this.logger.info("UseCase - iniciado processamento de requisição para transferir valor entre Carteiras.");

        Optional.of(transferencia)
            .map(this::validarCarteiras)
            .map(transfer -> {
                transfer.getBeneficiario().depositarNaCarteira(transfer.getValue());
                transfer.setDataTimeTransacao(OffsetDateTime.now());
                return transfer;
            })
            .map(this.transferenciaSalvarOutputPort::salvar)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("UseCase - concluído processamento de requisição para transferir valor entre Carteiras.");
    }

    private Transferencia validarCarteiras(Transferencia transferencia) {

        var pagador = this.carteiraBuscarPorIdInputPort.buscarPorId(transferencia.getPayer());
        transferencia.setPagador(pagador);

        var beneficiario = this.carteiraBuscarPorIdInputPort.buscarPorId(transferencia.getPayee());
        transferencia.setBeneficiario(beneficiario);

        return transferencia;
    }
}

