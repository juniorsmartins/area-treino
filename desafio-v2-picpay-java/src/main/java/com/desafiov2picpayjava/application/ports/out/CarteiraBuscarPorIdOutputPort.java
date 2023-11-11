package com.desafiov2picpayjava.application.ports.out;

import com.desafiov2picpayjava.application.core.domain.Carteira;

import java.util.Optional;

public interface CarteiraBuscarPorIdOutputPort {

    Optional<Carteira> buscarPorId(Long id);
}

