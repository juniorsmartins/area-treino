package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ContaBancariaTest {

    @Test
    void deve_Retornar_Conta_Bancaria_Quando_Criar_Com_Valor_Valido() {
        var valorNegativoInvalido = -10;

        var conta = new ContaBancaria(BigDecimal.ZERO);
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.ZERO));

        conta = new ContaBancaria(BigDecimal.TEN);
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.TEN));

        conta = new ContaBancaria(BigDecimal.valueOf(valorNegativoInvalido));
        Assertions.assertTrue(conta.saldo().equals(BigDecimal.valueOf(valorNegativoInvalido)));
    }

    @Test
    void deve_Lancar_Illegal_Exception_Ao_Criar_Com_Valor_Nulo() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(null));
    }

    @Test
    void deve_Retornar_O_Valor_Sacado() {

        var conta = new ContaBancaria(BigDecimal.TEN);
        var valorSacado = conta.saque(BigDecimal.ONE);

        Assertions.assertEquals(BigDecimal.ONE, valorSacado);
    }

    @Test
    void deve_Subtrair_Valor_Sacado() {
        var saldoDescontado = 9;

        var conta = new ContaBancaria(BigDecimal.TEN);
        conta.saque(BigDecimal.ONE);

        Assertions.assertEquals(BigDecimal.valueOf(saldoDescontado), conta.saldo());
    }

    @Test
    void deve_Lancar_Illegal_Argument_Exception_Ao_Sacar_Com_Valor_Inválido() {
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

