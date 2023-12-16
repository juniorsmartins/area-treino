package com.algaworks.junit.utilidade;

import java.math.BigDecimal;

public class ContaBancaria {

    private BigDecimal saldo;

    public ContaBancaria(BigDecimal saldo) {
        //TODO 1 - validar saldo: não pode ser nulo, caso seja, deve lançar uma IllegalArgumentException
        //TODO 2 - pode ser zero ou negativo

        if (saldo == null) {
            throw new IllegalArgumentException();
        }
        this.saldo = saldo;
    }

    public BigDecimal saque(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        //TODO 2 - Deve subtrair o valor do saldo
        //TODO 3 - Se o saldo for insuficiente deve lançar uma RuntimeException

        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0 || valor.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException();
        }

        if (valor.compareTo(this.saldo) > 0) {
            throw new RuntimeException();
        }

        this.saldo = this.saldo.subtract(valor);

        return valor;
    }

    public void deposito(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        //TODO 2 - Deve adicionar o valor ao saldo

        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0 || valor.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException();
        }

        this.saldo = this.saldo.add(valor);
    }

    public BigDecimal saldo() {
        return this.saldo;
    }
}
