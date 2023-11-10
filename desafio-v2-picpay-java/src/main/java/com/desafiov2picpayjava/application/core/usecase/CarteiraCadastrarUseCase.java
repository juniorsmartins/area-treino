package com.desafiov2picpayjava.application.core.usecase;

import com.desafiov2picpayjava.application.core.domain.Carteira;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.in.CarteiraCadastrarInputPort;
import com.desafiov2picpayjava.application.ports.in.UsuarioBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.out.CarteiraSalvarOutputPort;

import java.math.BigDecimal;
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
    public Usuario cadastrar(Usuario usuario) {

        logger.info("UseCase - iniciado processamento de requisição para cadastrar Carteira.");

        var usuarioComCarteira = Optional.of(usuario)
            .map(user -> this.usuarioBuscarPorIdInputPort.buscarPorId(user.getId()))
            .map(user -> {
                var carteira = new Carteira(BigDecimal.ZERO, usuario);
                carteira = this.carteiraSalvarOutputPort.salvar(carteira);
                user.setCarteira(carteira);
                return user;
            })
            .orElseThrow(NoSuchElementException::new);

        logger.info("UseCase - concluído processamento de requisição para cadastrar Carteira.");

        return usuarioComCarteira;
    }
}

