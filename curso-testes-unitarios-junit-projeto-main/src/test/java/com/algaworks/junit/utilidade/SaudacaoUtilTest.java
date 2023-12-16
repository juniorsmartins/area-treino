package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}

