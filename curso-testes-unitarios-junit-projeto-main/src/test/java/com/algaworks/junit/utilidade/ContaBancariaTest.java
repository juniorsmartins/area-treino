package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {

    @Test
    void deveRetornarContaBancariaQuandoCriarComValorValido() {
        var valorNegativoInvalido = -10;

        var conta = new ContaBancaria(BigDecimal.ZERO);
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.ZERO));

        conta = new ContaBancaria(BigDecimal.TEN);
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.TEN));

        conta = new ContaBancaria(BigDecimal.valueOf(valorNegativoInvalido));
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.valueOf(valorNegativoInvalido)));
    }

    @Test
    void deveLancarIllegalExceptionAoCriarComValorNulo() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(null));
    }

    @Test
    void deveRetornarOValorSacado() {

        var conta = new ContaBancaria(BigDecimal.TEN);
        var valorSacado = conta.saque(BigDecimal.ONE);

        Assertions.assertEquals(BigDecimal.ONE, valorSacado);
    }

    @Test
    void deveSubtrairValorSacado() {
        var saldoDescontado = 9;

        var conta = new ContaBancaria(BigDecimal.TEN);
        conta.saque(BigDecimal.ONE);

        Assertions.assertEquals(BigDecimal.valueOf(saldoDescontado), conta.saldo());
    }

    @Test
    void deveLancarIllegalArgumentExceptionAoSacarComValorInválido() {
        var valorNegativoInvalido = -5;

        var conta = new ContaBancaria(BigDecimal.TEN);
        Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(BigDecimal.ZERO));
        Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(BigDecimal.valueOf(valorNegativoInvalido)));
    }

    @Test
    void deveLancarRuntimeExceptionAoSacarComSaldoInsuficiente() {

        var conta = new ContaBancaria(BigDecimal.ONE);
        Assertions.assertThrows(RuntimeException.class, () -> conta.saque(BigDecimal.TEN));
    }
}

