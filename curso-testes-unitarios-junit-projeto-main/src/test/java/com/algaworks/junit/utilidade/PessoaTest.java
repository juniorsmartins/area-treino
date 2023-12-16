package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes de Pessoa")
class PessoaTest {

    @Test
    void assercaoAgrupada() {

        var pessoa = new Pessoa("Alex", "Silva");

        Assertions.assertAll("Asserções de Pessoa",
                () -> Assertions.assertEquals("Alex", pessoa.getNome()),
                () -> Assertions.assertEquals("Silva", pessoa.getSobrenome()));
    }
}

