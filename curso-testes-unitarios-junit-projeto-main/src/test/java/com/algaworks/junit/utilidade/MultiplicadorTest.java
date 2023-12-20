package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicadorTest {

    @ParameterizedTest
    @EnumSource(value = Multiplicador.class, names = {"DOBRO", "TRIPLO"}) // Aqui vão os enums
    void aplicarMultiplicador(Multiplicador multiplicador) {
        Assertions.assertNotNull(multiplicador.aplicarMultiplicador(10.0));
    }

    @ParameterizedTest
    @EnumSource(value = Multiplicador.class) // Se não especificar quais valores do Enum, então testa todos
    void aplicarMultiplicadorTodos(Multiplicador multiplicador) {
        Assertions.assertNotNull(multiplicador.aplicarMultiplicador(10.0));
    }
}

