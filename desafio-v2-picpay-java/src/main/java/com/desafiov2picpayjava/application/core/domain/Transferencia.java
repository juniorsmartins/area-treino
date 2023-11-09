package com.desafiov2picpayjava.application.core.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public final class Transferencia {

    private Carteira emitente;

    private Carteira recebedor;

    private BigDecimal valor;

    private OffsetDateTime dataTimeTransacao;
}

