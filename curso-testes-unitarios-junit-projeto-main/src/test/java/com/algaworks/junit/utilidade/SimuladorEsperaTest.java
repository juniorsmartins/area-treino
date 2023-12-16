package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

@DisplayName("Testes de Simulação de Espera")
class SimuladorEsperaTest {

    @Test
    void deveEsperarENaoDarTimeout() {

//        Assertions.assertTimeout(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofSeconds(10)));
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofMillis(10)));
    }
}

