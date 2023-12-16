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

    @Test
    @DisplayName("Calcular Valor, com bônus, por Palavra, por Texto e total")
    void dadoValoresValidos_QuandoCalcularGanhosComBonusDeDez_EntaoRetornarValorPorPalavraAndTextoAndTotal() {
        Ganhos ganhos = calculadora.calcular(post);

        Assertions.assertEquals(BigDecimal.valueOf(45), ganhos.getTotalGanho());
        Assertions.assertEquals(7, ganhos.getQuantidadePalavras());
        Assertions.assertEquals(autor.getValorPagoPorPalavra(), ganhos.getValorPagoPorPalavra());
    }

    @Test
    @DisplayName("Calcular Valor, sem bônus, por Palavra, por Texto e total")
    void dadoValoresValidos_QuandoCalcularGanhosSemBonus_EntaoRetornarValorPorPalavraAndTextoAndTotal() {
        autor.setPremium(false);

        Ganhos ganhos = calculadora.calcular(post);

        Assertions.assertEquals(BigDecimal.valueOf(35), ganhos.getTotalGanho());
        Assertions.assertEquals(7, ganhos.getQuantidadePalavras());
        Assertions.assertEquals(autor.getValorPagoPorPalavra(), ganhos.getValorPagoPorPalavra());
    }
}

