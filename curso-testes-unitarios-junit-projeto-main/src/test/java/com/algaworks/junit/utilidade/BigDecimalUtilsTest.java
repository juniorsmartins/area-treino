package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BigDecimalUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "10.00,10",
            "9.00,9.00",
            "9,9.00",
            "8,8.0"
    })
    void iguais(BigDecimal x, BigDecimal y) {
        Assertions.assertTrue(BigDecimalUtils.iguais(x, y));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/numeros.csv")
    void diferentes(BigDecimal x, BigDecimal y) {
        Assertions.assertFalse(BigDecimalUtils.iguais(x, y));
    }
}

