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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carteira getEmitente() {
        return emitente;
    }

    public void setEmitente(Carteira emitente) {
        this.emitente = emitente;
    }

    public Carteira getRecebedor() {
        return recebedor;
    }

    public void setRecebedor(Carteira recebedor) {
        this.recebedor = recebedor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public OffsetDateTime getDataTimeTransacao() {
        return dataTimeTransacao;
    }

    public void setDataTimeTransacao(OffsetDateTime dataTimeTransacao) {
        this.dataTimeTransacao = dataTimeTransacao;
    }
}

