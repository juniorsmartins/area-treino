package com.desafiov2picpayjava.adapters.out;

import com.desafiov2picpayjava.adapters.out.mappers.CarteiraOrmMapper;
import com.desafiov2picpayjava.adapters.out.repositories.CarteiraRepository;
import com.desafiov2picpayjava.application.core.domain.Carteira;
import com.desafiov2picpayjava.application.ports.out.CarteiraSalvarOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class CarteiraSalvarAdapter implements CarteiraSalvarOutputPort {

    private final Logger logger = Logger.getLogger(CarteiraSalvarAdapter.class.getName());

    private final CarteiraRepository carteiraRepository;

    private final CarteiraOrmMapper carteiraOrmMapper;

    @Transactional
    @Override
    public Carteira salvar(Carteira carteira) {

        logger.info("Adapter - iniciada persistência de Carteira no banco de dados.");

        var carteiraSalva = Optional.of(carteira)
            .map(this.carteiraOrmMapper::toCarteiraOrm)
            .map(this.carteiraRepository::save)
            .map(this.carteiraOrmMapper::toCarteira)
            .orElseThrow(NoSuchElementException::new);

        logger.info("Adapter - concluída persistência de Carteira no banco de dados.");

        return carteiraSalva;
    }
}

