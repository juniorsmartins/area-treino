package com.desafiov2picpayjava.application.core.domain;

import java.math.BigDecimal;

public final class Carteira {

    private long version;

    private Long id;

    private BigDecimal saldo;

    public Carteira() {}

    public Carteira(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Carteira(Long id, BigDecimal saldo) {
        this.id = id;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}

