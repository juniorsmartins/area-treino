package com.desafiov2picpayjava.application.core.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public final class Transferencia {

    private long version;

    private Long id;

    private Carteira emitente;

    private Carteira recebedor;

    private BigDecimal valor;

    private OffsetDateTime dataTimeTransacao;
}

