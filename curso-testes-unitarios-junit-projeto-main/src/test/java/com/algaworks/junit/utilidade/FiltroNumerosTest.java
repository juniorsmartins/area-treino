package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de Números")
class FiltroNumerosTest {

    @Test
    void deveRetornarNumerosPares() {

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        List<Integer> numerosParesEsperados = Arrays.asList(2, 4);

        List<Integer> resultadoFiltro = FiltroNumeros.numerosPares(numeros);

        Assertions.assertIterableEquals(numerosParesEsperados, resultadoFiltro);
    }
}

