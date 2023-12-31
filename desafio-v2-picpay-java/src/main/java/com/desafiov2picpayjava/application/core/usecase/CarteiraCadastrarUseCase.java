package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Carteira;
import com.desafiov2picpayjava.application.ports.in.CarteiraCadastrarInputPort;
import com.desafiov2picpayjava.application.ports.in.UsuarioBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.out.CarteiraSalvarOutputPort;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

public class CarteiraCadastrarUseCase implements CarteiraCadastrarInputPort {

    private final Logger logger = Logger.getLogger(CarteiraCadastrarUseCase.class.getName());

    private final UsuarioBuscarPorIdInputPort usuarioBuscarPorIdInputPort;

    private final CarteiraSalvarOutputPort carteiraSalvarOutputPort;

    public CarteiraCadastrarUseCase(UsuarioBuscarPorIdInputPort usuarioBuscarPorIdInputPort,
                                    CarteiraSalvarOutputPort carteiraSalvarOutputPort) {
        this.usuarioBuscarPorIdInputPort = usuarioBuscarPorIdInputPort;
        this.carteiraSalvarOutputPort = carteiraSalvarOutputPort;
    }

    @Override
    public Carteira cadastrar(Carteira carteira) {

        this.logger.info("UseCase - iniciado processamento de requisição para cadastrar Carteira.");

        var usuarioComCarteira = Optional.of(carteira)
            .map(this::validarUsuario)
            .map(this.carteiraSalvarOutputPort::salvar)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("UseCase - concluído processamento de requisição para cadastrar Carteira.");

        return usuarioComCarteira;
    }

    private Carteira validarUsuario(Carteira carteira) {
        var user = this.usuarioBuscarPorIdInputPort.buscarPorId(carteira.getUsuario().getId());
        carteira.setUsuario(user);
        return carteira;
    }
}

