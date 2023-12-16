package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {

    @Test
    void saudar() {

        String saudacao = SaudacaoUtil.saudar(9);

        Assertions.assertEquals("Bom dia", saudacao, "Saudação Incorreta!");
    }

    @Test
    void deveLancarException() {

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> SaudacaoUtil.saudar(-10));
        Assertions.assertEquals("Hora inválida", excecao.getMessage());
    }

    @Test
    void naoDeveLancarExcecao() {

        Assertions.assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
    }

    @Test
    @Disabled("forma de como desabilitar um teste")
    void comoDesabilitarTeste() {

        Assertions.assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
    }

    @Test
    void comoDesabilitarPorCondicional() {

        Assumptions.assumeTrue("PROD".equals(System.getenv("ENV")), () -> "Abortando Teste - não deve ser executado em PROD."); // Se a variável de ambiente for igual a PROD, não deve ser executado
        Assertions.assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "PROD") // Se a variável de ambiente for igual a PROD, deve ser executado
    void comoDesabilitarPorCondicional2() {

        Assertions.assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
    }
}

