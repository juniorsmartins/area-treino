package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {

    @Test
    void deveRetornarContaBancariaQuandoCriarComValorValido() {

        var conta = new ContaBancaria(BigDecimal.ZERO);
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.ZERO));

        conta = new ContaBancaria(BigDecimal.TEN);
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.TEN));

        conta = new ContaBancaria(BigDecimal.valueOf(-10));
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.valueOf(-10)));
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

        var conta = new ContaBancaria(BigDecimal.TEN);
        conta.saque(BigDecimal.ONE);

        Assertions.assertEquals(BigDecimal.valueOf(9), conta.saldo());
    }

    @Test
    void deveLancarIllegalArgumentExceptionAoSacarComValorInválido() {

        var conta = new ContaBancaria(BigDecimal.TEN);
        Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(BigDecimal.ZERO));
        Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(BigDecimal.valueOf(-5)));
    }

    @Test
    void deveLancarRuntimeExceptionAoSacarComSaldoInsuficiente() {

        var conta = new ContaBancaria(BigDecimal.ONE);
        Assertions.assertThrows(RuntimeException.class, () -> conta.saque(BigDecimal.TEN));
    }
}

