package com.desafiov2picpayjava.application.core.domain;

import java.math.BigDecimal;

public final class Carteira {

    private long version;

    private Long id;

    private BigDecimal saldo;

    private Usuario usuario;

    public Carteira() {}

    public Carteira(BigDecimal saldo, Usuario usuario) {
        this.saldo = saldo;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

