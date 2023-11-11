package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Carteira;
import com.desafiov2picpayjava.application.ports.in.CarteiraBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.in.CarteiraDepositarInputPort;
import com.desafiov2picpayjava.application.ports.out.CarteiraSalvarOutputPort;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class CarteiraDepositarUseCase implements CarteiraDepositarInputPort {

    private final Logger logger = Logger.getLogger(CarteiraDepositarUseCase.class.getName());

    private final CarteiraBuscarPorIdInputPort carteiraBuscarPorIdInputPort;

    private final CarteiraSalvarOutputPort carteiraSalvarOutputPort;

    public CarteiraDepositarUseCase(CarteiraBuscarPorIdInputPort carteiraBuscarPorIdInputPort,
                                    CarteiraSalvarOutputPort carteiraSalvarOutputPort) {
        this.carteiraBuscarPorIdInputPort = carteiraBuscarPorIdInputPort;
        this.carteiraSalvarOutputPort = carteiraSalvarOutputPort;
    }

    @Override
    public Carteira depositar(Carteira carteira) {

        this.logger.info("UseCase - iniciado processamento de requisição para depositar valor na Carteira.");

        var carteiraAtualizada = Optional.of(carteira)
            .map(conta -> {
                var carteiraDoDatabase = this.carteiraBuscarPorIdInputPort.buscarPorId(conta.getId());
                carteiraDoDatabase.depositarNaCarteira(conta.getSaldo());
                return carteiraDoDatabase;
            })
            .map(this.carteiraSalvarOutputPort::salvar)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("UseCase - concluído processamento de requisição para depositar valor na Carteira.");

        return carteiraAtualizada;
    }
}

