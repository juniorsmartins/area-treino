package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;
import com.algaworks.junit.blog.modelo.Ganhos;
import com.algaworks.junit.blog.modelo.Post;
import com.algaworks.junit.blog.utilidade.ProcessadorTextoSimples;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Calculadora de Ganhos")
class CalculadoraGanhosTest {

    static CalculadoraGanhos calculadora;

    private Editor autor;

    private Post post;

    @BeforeAll
    static void initAll() {
        System.out.println("Executa uma vez antes de todos os testes.");

        calculadora = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
    }

    @BeforeEach
    void init() {
        System.out.println("Executa uma vez antes de cada teste.");

        autor = new Editor(1L, "Robert Martin", "unclebob@email.com", BigDecimal.valueOf(5), true);
        post = new Post(1L, "Ecossistema Java", "O ecossistema do Java é muito maduro", autor,
                "ecossistema-java-abc123", null, false, false);
    }

    @AfterAll
    static void finishAll() {
        System.out.println("Executa uma vez depois de todos os testes.");
    }

    @AfterEach
    void finish() {
        System.out.println("Executa uma vez depois de cada teste.");
    }

    @Nested
    class CalcularComBonus {

        @Test
        @DisplayName("Calcular Valor Total, com bônus.")
        void dadoValorValido_QuandoCalcularGanhoComBonus_EntaoRetornarValorTotal() {
            Ganhos ganhos = calculadora.calcular(post);
            Assertions.assertEquals(BigDecimal.valueOf(45), ganhos.getTotalGanho());
        }

        @Test
        @DisplayName("Calcular Valor por Palavra, com bônus.")
        void dadoValorValido_QuandoCalcularGanhoComBonus_EntaoRetornarValorPorPalavra() {
            Ganhos ganhos = calculadora.calcular(post);
            Assertions.assertEquals(autor.getValorPagoPorPalavra(), ganhos.getValorPagoPorPalavra());
        }

        @Test
        @DisplayName("Calcular Quantidade de Palavras")
        void dadoValorValido_QuandoCalcularGanhoComBonus_EntaoRetornarQuantidadeDePalavras() {
            Ganhos ganhos = calculadora.calcular(post);
            Assertions.assertEquals(7, ganhos.getQuantidadePalavras());
        }
    }

    @Nested
    class CalcularSemBonus {

        @Test
        @DisplayName("Calcular Valor Total, sem bônus.")
        void dadoValorValido_QuandoCalcularGanhoSemBonus_EntaoRetornarValorTotal() {
            autor.setPremium(false);
            Ganhos ganhos = calculadora.calcular(post);
            Assertions.assertEquals(BigDecimal.valueOf(35), ganhos.getTotalGanho());
        }

        @Test
        @DisplayName("Calcular Valor Por Palavra, sem bônus.")
        void dadoValorValido_QuandoCalcularGanhoSemBonus_EntaoRetornarValorPorPalavra() {
            autor.setPremium(false);
            Ganhos ganhos = calculadora.calcular(post);
            Assertions.assertEquals(autor.getValorPagoPorPalavra(), ganhos.getValorPagoPorPalavra());
        }

        @Test
        @DisplayName("Calcular Quantidade de Palavras.")
        void dadoValorValido_QuandoCalcularGanhoSemBonus_EntaoRetornarQuantidadeDePalavras() {
            autor.setPremium(false);
            Ganhos ganhos = calculadora.calcular(post);
            Assertions.assertEquals(7, ganhos.getQuantidadePalavras());
        }
    }
}

