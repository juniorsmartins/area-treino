package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {

    public static final String SAUDACAO_BOM_DIA = "Bom dia";
    public static final String SAUDACAO_BOA_TARDE = "Boa tarde";
    public static final String SAUDACAO_BOA_NOITE = "Boa noite";
    public static final String SAUDACAO_INCORRETA = "Saudação Incorreta!";

    @Test
    void saudar() {
        var horaBomDia = 9;
        String saudacao = SaudacaoUtil.saudar(horaBomDia);
        Assertions.assertEquals(SAUDACAO_BOM_DIA, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMinimoDaCondicaoBomDia() {
        var horaLimiteInferior = 0;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(SAUDACAO_BOM_DIA, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMaximoDaCondicaoBomDia() {
        var horaLimiteSuperior = 11;
        var saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(SAUDACAO_BOM_DIA, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMinimoDaCondicaoBoaTarde() {
        var horaLimiteInferior = 12;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(SAUDACAO_BOA_TARDE, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMaximoDaCondicaoBoaTarde() {
        var horaLimiteSuperior = 17;
        var saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(SAUDACAO_BOA_TARDE, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMinimoDaCondicaoBoaNoite() {
        var horaLimiteInferior = 18;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(SAUDACAO_BOA_NOITE, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    void deveTestarLimitesMaximoDaCondicaoBoaNoite() {
        var horaLimiteSuperior = 23;
        var saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(SAUDACAO_BOA_NOITE, saudacao, SAUDACAO_INCORRETA);
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
        var horaValida = 0;
        Executable acaoExecutavel = () -> SaudacaoUtil.saudar(horaValida);
        Assertions.assertDoesNotThrow(acaoExecutavel);
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

