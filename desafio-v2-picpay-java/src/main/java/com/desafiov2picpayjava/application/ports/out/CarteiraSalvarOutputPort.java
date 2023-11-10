package com.desafiov2picpayjava.application.ports.out;

import com.desafiov2picpayjava.application.core.domain.Carteira;

public interface CarteiraSalvarOutputPort {

    Carteira salvar(Carteira carteira);
}

