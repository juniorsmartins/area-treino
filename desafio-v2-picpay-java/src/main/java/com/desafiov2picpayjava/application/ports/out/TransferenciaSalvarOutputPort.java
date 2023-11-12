package com.desafiov2picpayjava.application.ports.out;

import com.desafiov2picpayjava.application.core.domain.Transferencia;

public interface TransferenciaSalvarOutputPort {

    Transferencia salvar(Transferencia transferencia);
}

