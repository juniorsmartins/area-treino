package com.desafiov2picpayjava.application.ports.in;

import com.desafiov2picpayjava.application.core.domain.Carteira;

public interface CarteiraBuscarPorIdInputPort {

    Carteira buscarPorId(Long id);
}

