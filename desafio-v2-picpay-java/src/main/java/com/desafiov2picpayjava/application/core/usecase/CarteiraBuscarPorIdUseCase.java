package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Carteira;
import com.desafiov2picpayjava.application.ports.in.CarteiraBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.out.CarteiraBuscarPorIdOutputPort;
import com.desafiov2picpayjava.config.exceptions.http_404.CarteiraNaoEncontradaPorIdException;

import java.util.logging.Logger;

public class CarteiraBuscarPorIdUseCase implements CarteiraBuscarPorIdInputPort {

    private final Logger logger = Logger.getLogger(CarteiraBuscarPorIdUseCase.class.getName());

    private final CarteiraBuscarPorIdOutputPort carteiraBuscarPorIdOutputPort;

    public CarteiraBuscarPorIdUseCase(CarteiraBuscarPorIdOutputPort carteiraBuscarPorIdOutputPort) {
        this.carteiraBuscarPorIdOutputPort = carteiraBuscarPorIdOutputPort;
    }

    @Override
    public Carteira buscarPorId(final Long id) {

        this.logger.info("UseCase - iniciado processamento de requisição de buscar Carteira por id.");

        var carteiraBuscada = this.carteiraBuscarPorIdOutputPort.buscarPorId(id)
            .orElseThrow(() -> new CarteiraNaoEncontradaPorIdException(id));

        this.logger.info("UseCase - concluído processamento de requisição de buscar Carteira por id.");

        return carteiraBuscada;
    }
}

