package com.desafiov2picpayjava.application.ports.out;

import com.desafiov2picpayjava.application.core.domain.Carteira;

public interface CarteiraBuscarPorIdOutputPort {

    Carteira buscarPorId(Long id);
}

