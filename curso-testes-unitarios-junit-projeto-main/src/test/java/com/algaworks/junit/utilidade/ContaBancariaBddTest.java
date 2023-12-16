package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

@DisplayName("Testes de Conta Bancária")
class ContaBancariaBddTest {

    @Nested
    @DisplayName("Testes de Saldo")
    class Saldo {

        private ContaBancaria conta;

        @Test
        @DisplayName("Criar Conta com Saldo Zero.")
        void dadoValorZerado_QuandoCriarContaBancaria_EntaoRetornarSaldoZero() {
            var saldoInicial = BigDecimal.ZERO;
            conta = new ContaBancaria(saldoInicial);
            Assertions.assertEquals(conta.saldo(), saldoInicial);
        }

        @Test
        @DisplayName("Criar Conta com Saldo Dez.")
        void dadoValorDez_QuandoCriarContaBancaria_EntaoRetornarSaldoDez() {
            var saldoInicial = BigDecimal.TEN;
            conta = new ContaBancaria(saldoInicial);
            Assertions.assertEquals(conta.saldo(), saldoInicial);
        }

        @Test
        @DisplayName("Criar Conta com Saldo Negativo.")
        void dadoValorNegativo_QuandoCriarContaBancaria_EntaoRetornarSaldoNegativo() {
            var valorNegativoValido = BigDecimal.valueOf(-10);
            conta = new ContaBancaria(valorNegativoValido);
            Assertions.assertEquals(conta.saldo(), valorNegativoValido);
        }

        @Test
        @DisplayName("Lançar Exceção ao Criar Conta com Saldo Nulo.")
        void dadoValorNulo_QuandoCriarContaBancaria_EntaoRetornarIllegalArgumentException() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(null));
        }
    }

    @Nested
    @DisplayName("Testes de Saque")
    class Saque {

        private ContaBancaria conta;

        @BeforeEach
        void criarCenarioDeTeste() {
            conta = new ContaBancaria(BigDecimal.TEN);
        }

        @Test
        @DisplayName("Sacar Conta com Valor Positivo.")
        void dadoValorPositivo_QuandoSacarContaBancaria_EntaoRetornarValorSacado() {
            var valorSacado = conta.saque(BigDecimal.ONE);
            Assertions.assertEquals(BigDecimal.ONE, valorSacado);
        }

        @Test
        @DisplayName("Sacar Conta e Subtrair do Saldo.")
        void dadoValorPositivo_QuandoSacarContaBancaria_EntaoSubtrairValorSacadoDoSaldo() {
            var saldoDescontado = BigDecimal.valueOf(9);
            conta.saque(BigDecimal.ONE);
            Assertions.assertEquals(saldoDescontado, conta.saldo());
        }

        @Test
        @DisplayName("Lançar Exceção ao Sacar Conta com Valor Nulo.")
        void dadoValorNulo_QuandoSacarDeContaBancaria_EntaoRetornarIllegalArgumentException() {
            Executable acao = () -> conta.saque(null);
            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }

        @Test
        @DisplayName("Lançar Exceção ao Sacar Conta com Valor Zerado.")
        void dadoValorZerado_QuandoSacarDeContaBancaria_EntaoLancarIllegalArgumentException() {
            Executable acao = () -> conta.saque(BigDecimal.ZERO);
            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }

        @Test
        @DisplayName("Lançar Exceção ao Sacar Conta com Valor Negativo.")
        void dadoValorNegativo_QuandoSacarDeContaBancaria_EntaoLancarIllegalArgumentException() {
            var valorNegativoInvalido = BigDecimal.valueOf(-5);
            Executable acao = () -> conta.saque(valorNegativoInvalido);
            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }

        @Test
        @DisplayName("Lançar Exceção ao Sacar Conta com Saldo Insuficiente.")
        void deveLancarRuntimeExceptionAoSacarComSaldoInsuficiente() {
            var saqueMaior = BigDecimal.valueOf(20);
            Executable acao = () -> conta.saque(saqueMaior);
            Assertions.assertThrows(RuntimeException.class, acao);
        }
    }

    @Nested
    @DisplayName("Testes de Depósito")
    class Deposito {

        private ContaBancaria conta;

        @Test
        @DisplayName("Depositar Valor Positivo em Conta.")
        void dadoValorPositivo_QuandoDepositar_EntaoRetornarSaldoAtualizado() {
            var saldoInicial = BigDecimal.ONE;
            var valorPositivo = BigDecimal.TEN;

            conta = new ContaBancaria(saldoInicial);
            conta.deposito(valorPositivo);

            var saldoAtual = saldoInicial.add(valorPositivo);
            Assertions.assertEquals(saldoAtual, conta.saldo());
        }

        @Test
        @DisplayName("Lançar Exceção ao Depositar Valor Nulo em Conta.")
        void dadoValorNulo_QuandoDepositar_EntaoLancarIllegalArgumentException() {
            var saldoInicial = BigDecimal.ONE;

            conta = new ContaBancaria(saldoInicial);

            Executable acao = () -> conta.deposito(null);
            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }

        @Test
        @DisplayName("Lançar Exceção ao Depositar Valor Zerado em Conta.")
        void dadoValorZerado_QuandoDepositar_EntaoLancarIllegalArgumentException() {
            var saldoInicial = BigDecimal.ONE;
            var valorZerado = BigDecimal.ZERO;

            conta = new ContaBancaria(saldoInicial);

            Executable acao = () -> conta.deposito(valorZerado);
            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }

        @Test
        @DisplayName("Lançar Exceção ao Depositar Valor Negativo em Conta.")
        void dadoValorNegativo_QuandoDepositar_EntaoLancarIllegalArgumentException() {
            var saldoInicial = BigDecimal.ONE;
            var valorNegativo = BigDecimal.valueOf(-5);

            conta = new ContaBancaria(saldoInicial);

            Executable acao = () -> conta.deposito(valorNegativo);
            Assertions.assertThrows(IllegalArgumentException.class, acao);
        }
    }
}

