package com.desafiov2picpayjava.application.core.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public final class Transferencia {

    private long version;

    private Long id;

    private BigDecimal value;

    private Long payer;

    private Carteira pagador;

    private Long payee;

    private Carteira beneficiario;

    private OffsetDateTime dataTimeTransacao;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getPayer() {
        return payer;
    }

    public void setPayer(Long payer) {
        this.payer = payer;
    }

    public Carteira getPagador() {
        return pagador;
    }

    public void setPagador(Carteira pagador) {
        this.pagador = pagador;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }

    public Carteira getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Carteira beneficiario) {
        this.beneficiario = beneficiario;
    }

    public OffsetDateTime getDataTimeTransacao() {
        return dataTimeTransacao;
    }

    public void setDataTimeTransacao(OffsetDateTime dataTimeTransacao) {
        this.dataTimeTransacao = dataTimeTransacao;
    }
}

