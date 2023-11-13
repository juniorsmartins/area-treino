package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Transferencia;
import com.desafiov2picpayjava.application.ports.in.TransferenciaEfetuarInputPort;
import com.desafiov2picpayjava.application.ports.out.CarteiraBuscarPorIdOutputPort;
import com.desafiov2picpayjava.application.ports.out.CarteiraSalvarOutputPort;
import com.desafiov2picpayjava.application.ports.out.TransferenciaSalvarOutputPort;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class TransferenciaEfetuarUseCase implements TransferenciaEfetuarInputPort {

    private final Logger logger = Logger.getLogger(TransferenciaEfetuarUseCase.class.getName());

    private final TransferenciaSalvarOutputPort transferenciaSalvarOutputPort;

    private final CarteiraBuscarPorIdOutputPort carteiraBuscarPorIdOutputPort;

    private final CarteiraSalvarOutputPort carteiraSalvarOutputPort;

    public TransferenciaEfetuarUseCase(TransferenciaSalvarOutputPort transferenciaSalvarOutputPort,
                                       CarteiraBuscarPorIdOutputPort carteiraBuscarPorIdOutputPort,
                                       CarteiraSalvarOutputPort carteiraSalvarOutputPort) {
        this.transferenciaSalvarOutputPort = transferenciaSalvarOutputPort;
        this.carteiraBuscarPorIdOutputPort = carteiraBuscarPorIdOutputPort;
        this.carteiraSalvarOutputPort = carteiraSalvarOutputPort;
    }

    @Transactional
    @Override
    public void transferir(Transferencia transferencia) {

        this.logger.info("UseCase - iniciado processamento de requisição para transferir valor entre Carteiras.");

        Optional.of(transferencia)
            .map(this::validarCarteiras)
            .map(this::efetuarTransferencia)
            .map(this.transferenciaSalvarOutputPort::salvar)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("UseCase - concluído processamento de requisição para transferir valor entre Carteiras.");
    }

    private Transferencia validarCarteiras(Transferencia transferencia) {

        var pagador = this.carteiraBuscarPorIdOutputPort.buscarPorId(transferencia.getPayer());
        transferencia.setPagador(pagador);

        var beneficiario = this.carteiraBuscarPorIdOutputPort.buscarPorId(transferencia.getPayee());
        transferencia.setBeneficiario(beneficiario);

        return transferencia;
    }

    private Transferencia efetuarTransferencia(Transferencia transfer) {

        transfer.getPagador().retirarDaCarteira(transfer.getValue());
        this.carteiraSalvarOutputPort.salvar(transfer.getPagador());

        transfer.getBeneficiario().depositarNaCarteira(transfer.getValue());
        this.carteiraSalvarOutputPort.salvar(transfer.getBeneficiario());

        transfer.setDataTimeTransacao(OffsetDateTime.now());
        return transfer;
    }
}

