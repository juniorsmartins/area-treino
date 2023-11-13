package com.desafiov2picpayjava.adapters.out;

import com.desafiov2picpayjava.adapters.out.mappers.CarteiraOrmMapper;
import com.desafiov2picpayjava.adapters.out.repositories.CarteiraRepository;
import com.desafiov2picpayjava.application.core.domain.Carteira;
import com.desafiov2picpayjava.application.ports.out.CarteiraBuscarPorIdOutputPort;
import com.desafiov2picpayjava.config.exceptions.http_404.CarteiraNaoEncontradaPorIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class CarteiraBuscarPorIdAdapter implements CarteiraBuscarPorIdOutputPort {

    private final Logger logger = Logger.getLogger(CarteiraBuscarPorIdAdapter.class.getName());

    private final CarteiraRepository carteiraRepository;

    private final CarteiraOrmMapper carteiraOrmMapper;

    @Transactional(readOnly = true)
    @Override
    public Carteira buscarPorId(final Long id) {

        this.logger.info("Adapter - iniciado buscar Carteira por id no banco de dados.");

        var carteiraBuscada = this.carteiraRepository.findById(id)
            .map(this.carteiraOrmMapper::toCarteira)
            .orElseThrow(() -> new CarteiraNaoEncontradaPorIdException(id));

        this.logger.info("Adapter - concluído buscar Carteira por id no banco de dados.");

        return carteiraBuscada;
    }
}

