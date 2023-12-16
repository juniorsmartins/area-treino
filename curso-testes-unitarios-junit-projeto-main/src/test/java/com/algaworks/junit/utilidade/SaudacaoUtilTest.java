package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {

    public static final String SAUDACAO_INCORRETA = "Saudação Incorreta!";

    @Test
    void saudar() {
        var saudacaoBomDia = "Bom dia";
        var horaBomDia = 9;
        String saudacao = SaudacaoUtil.saudar(horaBomDia);
        Assertions.assertEquals(saudacaoBomDia, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMinimoAndMaximoDaCondicaoBomDia() {
        var saudacaoCorreta = "Bom dia";

        var horaLimiteInferior = 0;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(saudacaoCorreta, saudacao, SAUDACAO_INCORRETA);

        var horaLimiteSuperior = 11;
        saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(saudacaoCorreta, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMinimoAndMaximoDaCondicaoBoaTarde() {
        var saudacaoCorreta = "Boa tarde";

        var horaLimiteInferior = 12;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(saudacaoCorreta, saudacao, SAUDACAO_INCORRETA);

        var horaLimiteSuperior = 17;
        saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(saudacaoCorreta, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMinimoAndMaximoDaCondicaoBoaNoite() {
        var saudacaoCorreta = "Boa noite";

        var horaLimiteInferior = 18;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(saudacaoCorreta, saudacao, SAUDACAO_INCORRETA);

        var horaLimiteSuperior = 23;
        saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(saudacaoCorreta, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveLancarException() {
        var horaNegativaInvalida = -10;

        Executable executavel = () -> SaudacaoUtil.saudar(horaNegativaInvalida);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, executavel);
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

