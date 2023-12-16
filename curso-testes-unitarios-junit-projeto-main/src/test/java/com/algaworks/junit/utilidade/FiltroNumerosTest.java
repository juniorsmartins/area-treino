package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@DisplayName("Testes de Números")
class FiltroNumerosTest {

    @Test
    void dadoListaDeNumeros_QuandoFiltrarPorPares_EntaoRetornarNumerosPares() {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        List<Integer> numerosParesEsperados = Arrays.asList(2, 4);

        List<Integer> resultadoFiltro = FiltroNumeros.numerosPares(numeros);

        Assertions.assertIterableEquals(numerosParesEsperados, resultadoFiltro);
    }

    @Test
    void dadoListaDeNumeros_QuandoFiltrarPorImpares_EntaoRetornarNumerosImpares() {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        List<Integer> numerosImparesEsperados = Arrays.asList(1, 3);

        List<Integer> resultadoFiltro = FiltroNumeros.numerosImpares(numeros);

        Assertions.assertIterableEquals(numerosImparesEsperados, resultadoFiltro);
    }
}

