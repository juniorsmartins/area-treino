package com.desafiov2picpayjava.application.ports.in;

import com.desafiov2picpayjava.application.core.domain.Carteira;

public interface CarteiraDepositarInputPort {

    Carteira depositar(Carteira carteira);
}

