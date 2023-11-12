package com.desafiov2picpayjava.application.ports.in;

import com.desafiov2picpayjava.application.core.domain.Transferencia;

public interface TransferenciaEfetuarInputPort {

    void transferir(Transferencia transferencia);
}

