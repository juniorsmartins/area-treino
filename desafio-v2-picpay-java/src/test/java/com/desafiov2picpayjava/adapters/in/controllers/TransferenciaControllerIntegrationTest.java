package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.TransferenciaDtoIn;
import com.desafiov2picpayjava.config.exceptions.dtos.ErrorMessage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/transferencias/transferencias-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/transferencias/transferencias-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransferenciaControllerIntegrationTest {

    private static final String CAMINHO = "/api/v1/transferencias";

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    public void fazerTransferencia_ComDadosValidos_RetornarHttpStatus204() {

        var dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(21L)
            .payee(20L)
            .build();

        this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isNoContent();
    }

    @Test
    @Order(2)
    public void fazerTransferencia_ComDadosInvalidos_RetornarErrorMessageComHttpStatus400() {

        var dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.valueOf(-10))
            .payer(21L)
            .payee(20L)
            .build();

        var resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.ZERO)
            .payer(21L)
            .payee(20L)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = TransferenciaDtoIn.builder()
            .value(null)
            .payer(21L)
            .payee(20L)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(null)
            .payee(20L)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(-1L)
            .payee(20L)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(0L)
            .payee(20L)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(21L)
            .payee(null)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(21L)
            .payee(-1L)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(21L)
            .payee(0L)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);
    }

    @Test
    @Order(3)
    public void fazerTransferencia_ComIdInexistente_RetornarErrorMessageComHttpStatus404() {

        var dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(2000L)
            .payee(20L)
            .build();

        var resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(404);

        dtoIn = TransferenciaDtoIn.builder()
            .value(BigDecimal.TWO)
            .payer(21L)
            .payee(2000L)
            .build();

        resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(404);
    }
}

