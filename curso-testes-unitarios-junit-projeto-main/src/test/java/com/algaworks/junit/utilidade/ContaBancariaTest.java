package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ContaBancariaTest {

    @Nested
    class Saldo {
        @Test
        void deve_Retornar_Conta_Bancaria_Quando_Criar_Com_Valor_Valido_Zero() {
            var saldoInicial = BigDecimal.ZERO;
            var conta = new ContaBancaria(saldoInicial);
            Assertions.assertEquals(conta.saldo(), saldoInicial);
        }

        @Test
        void deve_Retornar_Conta_Bancaria_Quando_Criar_Com_Valor_Valido_Dez() {
            var saldoInicial = BigDecimal.TEN;
            var conta = new ContaBancaria(saldoInicial);
            Assertions.assertEquals(conta.saldo(), saldoInicial);
        }

        @Test
        void deve_Retornar_Conta_Bancaria_Quando_Criar_Com_Valor_Valido_Negativo() {
            var valorNegativoValido = BigDecimal.valueOf(-10);
            var conta = new ContaBancaria(valorNegativoValido);
            Assertions.assertEquals(conta.saldo(), valorNegativoValido);
        }

        @Test
        void deve_Lancar_Illegal_Exception_Ao_Criar_Com_Valor_Nulo() {

            Assertions.assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(null));
        }
    }

    @Nested
    class Saque {
        @Test
        void deve_Retornar_O_Valor_Sacado() {

            var conta = new ContaBancaria(BigDecimal.TEN);
            var valorSacado = conta.saque(BigDecimal.ONE);

            Assertions.assertEquals(BigDecimal.ONE, valorSacado);
        }

        @Test
        void deve_Subtrair_Valor_Sacado() {
            var saldoDescontado = BigDecimal.valueOf(9);

            var conta = new ContaBancaria(BigDecimal.TEN);
            conta.saque(BigDecimal.ONE);

            Assertions.assertEquals(saldoDescontado, conta.saldo());
        }

        @Test
        void deve_Lancar_Illegal_Argument_Exception_Ao_Sacar_Com_Valor_Inválido() {
            var valorNegativoInvalido = BigDecimal.valueOf(-5);

            var conta = new ContaBancaria(BigDecimal.TEN);
            Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(null));
            Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(BigDecimal.ZERO));
            Assertions.assertThrows(IllegalArgumentException.class, () -> conta.saque(valorNegativoInvalido));
        }

        @Test
        void deveLancarRuntimeExceptionAoSacarComSaldoInsuficiente() {

            var conta = new ContaBancaria(BigDecimal.ONE);
            Assertions.assertThrows(RuntimeException.class, () -> conta.saque(BigDecimal.TEN));
        }
    }

    @Nested
    class Deposito {
        @Test
        void dadoValorPositivo_QuandoDepositar_EntaoRetornarSaldoAtual() {
            var saldoInicial = BigDecimal.ONE;
            var valorPositivo = BigDecimal.TEN;

            var conta = new ContaBancaria(saldoInicial);
            conta.deposito(valorPositivo);

            var saldoAtual = saldoInicial.add(valorPositivo);

            Assertions.assertEquals(saldoAtual, conta.saldo());
        }

        @Test
        void dadoValorNulo_QuandoDepositar_EntaoLancarIllegalArgumentException() {
            var saldoInicial = BigDecimal.ONE;

            var conta = new ContaBancaria(saldoInicial);

            Executable acao = () -> conta.deposito(null);

            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }

        @Test
        void dadoValorZerado_QuandoDepositar_EntaoLancarIllegalArgumentException() {
            var saldoInicial = BigDecimal.ONE;
            var valorZerado = BigDecimal.ZERO;

            var conta = new ContaBancaria(saldoInicial);

            Executable acao = () -> conta.deposito(valorZerado);

            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }

        @Test
        void dadoValorNegativo_QuandoDepositar_EntaoLancarIllegalArgumentException() {
            var saldoInicial = BigDecimal.ONE;
            var valorNegativo = BigDecimal.valueOf(-5);

            var conta = new ContaBancaria(saldoInicial);

            Executable acao = () -> conta.deposito(valorNegativo);

            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }
    }
}

