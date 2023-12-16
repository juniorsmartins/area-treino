package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Saudações")
class SaudacaoUtilTest {

    public static final String SAUDACAO_BOM_DIA = "Bom dia";
    public static final String SAUDACAO_BOA_TARDE = "Boa tarde";
    public static final String SAUDACAO_BOA_NOITE = "Boa noite";
    public static final String SAUDACAO_INCORRETA = "Saudação Incorreta!";

    // Princípio First
    @Test
    @DisplayName("Deve saudar com Bom Dia")
    void dadoUmHorario_QuandoSaudar_EntaoRetornarBomDia() { // Nomenclatura BDD
        var horaBomDia = 9; // Padrão Triple A
        String saudacao = SaudacaoUtil.saudar(horaBomDia);
        Assertions.assertEquals(SAUDACAO_BOM_DIA, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    @DisplayName("Limite Mínimo de Bom Dia!")
    void dadoUmHorarioLimiteInferior_QuandoSaudar_EntaoRetornarBomDia() {
        var horaLimiteInferior = 0;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(SAUDACAO_BOM_DIA, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    @DisplayName("Limite Máximo de Bom Dia!")
    void dadoUmHorarioLimiteMaximo_QuandoSaudar_EntaoRetornarBomDia() {
        var horaLimiteSuperior = 11;
        var saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(SAUDACAO_BOM_DIA, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    @DisplayName("Limite Mínimo de Boa Tarde!")
    void dadoUmHorarioLimiteMinimo_QuandoSaudar_EntaoRetornarBoaTarde() {
        var horaLimiteInferior = 12;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(SAUDACAO_BOA_TARDE, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    @DisplayName("Limite Máximo de Boa Tarde!")
    void dadoUmHorarioLimiteMaximo_QuandoSaudar_EntaoRetornarBoaTarde() {
        var horaLimiteSuperior = 17;
        var saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(SAUDACAO_BOA_TARDE, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    @DisplayName("Limite Mínimo de Boa Noite!")
    void dadoUmHorarioMininmo_QuandoSaudar_EntaoRetornarBoaNoite() {
        var horaLimiteInferior = 18;
        String saudacao = SaudacaoUtil.saudar(horaLimiteInferior);
        Assertions.assertEquals(SAUDACAO_BOA_NOITE, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    @DisplayName("Limite Máximo de Boa Noite!")
    void dadoUmHorarioLimiteMaximo_QuandoSaudar_EntaoRetornarBoaNoite() {
        var horaLimiteSuperior = 23;
        var saudacao = SaudacaoUtil.saudar(horaLimiteSuperior);
        Assertions.assertEquals(SAUDACAO_BOA_NOITE, saudacao, SAUDACAO_INCORRETA);
    }

    @Test
    @DisplayName("Exceção por Horário Negativo")
    void dadoUmHorarioNegativo_QuandoSaudar_EntaoLancarIllegalArgumentException() {
        var horaNegativaInvalida = -10;

        Executable executavel = () -> SaudacaoUtil.saudar(horaNegativaInvalida);

        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, executavel);
        Assertions.assertEquals("Hora inválida", excecao.getMessage());
    }

    @Test
    @DisplayName("Nao Lançar Exceção com Horário Zerado")
    void dadoUmHorarioZerado_QuandoSaudar_EntaoNaoDeveLancarExcecao() {
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

