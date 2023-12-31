package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.CarteiraBuscarDtoOut;
import com.desafiov2picpayjava.adapters.in.dtos.CarteiraCadastrarDtoOut;
import com.desafiov2picpayjava.adapters.in.dtos.CarteiraDepositarDtoOut;
import com.desafiov2picpayjava.adapters.in.dtos.UsuarioIdDto;
import com.desafiov2picpayjava.config.exceptions.dtos.ErrorMessage;
import com.desafiov2picpayjava.utils.CriadorDeBuilders;
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
@Sql(scripts = "/sql/carteiras/carteiras-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/carteiras/carteiras-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarteiraControllerIntegrationTest {

    private static final String CAMINHO = "/api/v1/carteiras";

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    public void cadastrarCarteira_ComDadosValidos_RetornarCarteiraCadastrarDtoOutComHttpStatus201() {

        var userId = UsuarioIdDto.builder()
            .id(15L)
            .build();

        var dtoIn = CriadorDeBuilders.gerarCarteiraCadastrarDtoInBuilder()
            .usuario(userId)
            .build();

        var resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(CarteiraCadastrarDtoOut.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.id()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.saldo()).isEqualTo(BigDecimal.ZERO);
        org.assertj.core.api.Assertions.assertThat(resposta.usuario()).isNotNull();
    }

    @Test
    @Order(2)
    public void cadastrarCarteira_ComDadosInvalidos_RetornarErrorMessageComHttpStatus400() {

        var userId = UsuarioIdDto.builder()
            .id(null)
            .build();

        var dtoIn = CriadorDeBuilders.gerarCarteiraCadastrarDtoInBuilder()
            .usuario(userId)
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

        userId = UsuarioIdDto.builder()
            .id(-1L)
            .build();

        dtoIn = CriadorDeBuilders.gerarCarteiraCadastrarDtoInBuilder()
            .usuario(userId)
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

        userId = UsuarioIdDto.builder()
            .id(0L)
            .build();

        dtoIn = CriadorDeBuilders.gerarCarteiraCadastrarDtoInBuilder()
            .usuario(userId)
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

        userId = UsuarioIdDto.builder()
            .id(15L)
            .build();

        dtoIn = CriadorDeBuilders.gerarCarteiraCadastrarDtoInBuilder()
            .usuario(userId)
            .saldo(null)
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

        userId = UsuarioIdDto.builder()
            .id(15L)
            .build();

        dtoIn = CriadorDeBuilders.gerarCarteiraCadastrarDtoInBuilder()
            .usuario(userId)
            .saldo(BigDecimal.valueOf(-10))
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
    public void cadastrarCarteira_ComIdDeUsuarioInexistente_RetornarErrorMessageComHttpStatus404() {

        var userId = UsuarioIdDto.builder()
            .id(1000L)
            .build();

        var dtoIn = CriadorDeBuilders.gerarCarteiraCadastrarDtoInBuilder()
            .usuario(userId)
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
    }

    @Test
    @Order(5)
    public void buscarCarteiraPorId_ComIdExistente_RetornarCarteiraBuscarDtoOutComHttpStatus200() {

        var resposta = this.webTestClient.get()
            .uri(CAMINHO.concat("/20"))
            .exchange()
            .expectStatus().isOk()
            .expectBody(CarteiraBuscarDtoOut.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.id()).isEqualTo(20);
        org.assertj.core.api.Assertions.assertThat(resposta.saldo()).isEqualTo(BigDecimal.TEN.setScale(2));
        org.assertj.core.api.Assertions.assertThat(resposta.usuario().id()).isEqualTo(16);
    }

    @Test
    @Order(6)
    public void buscarCarteiraPorId_ComIdInexistente_RetornarErrorMessageComHttpStatus404() {

        var resposta = this.webTestClient.get()
            .uri(CAMINHO.concat("/2000"))
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(404);
    }

    @Test
    @Order(10)
    public void depositarNaCarteira_ComIdExistenteAndDadosValidos_RetornarCarteiraDepositarDtoOutComHttpStatus200() {

        var dtoIn = CriadorDeBuilders.gerarCarteiraDepositarDtoInBuilder()
            .id(20L)
            .build();

        var resposta = this.webTestClient.put()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isOk()
            .expectBody(CarteiraDepositarDtoOut.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.id()).isEqualTo(20);
        org.assertj.core.api.Assertions.assertThat(resposta.saldo()).isEqualTo(BigDecimal.valueOf(20).setScale(2));
    }

    @Test
    @Order(11)
    public void depositarNaCarteira_ComIdInexistenteAndDadosValidos_RetornarErrorMessageComHttpStatus404() {

        var dtoIn = CriadorDeBuilders.gerarCarteiraDepositarDtoInBuilder()
            .id(2000L)
            .build();

        var resposta = this.webTestClient.put()
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

    @Test
    @Order(12)
    public void depositarNaCarteira_ComIdExistenteAndDadosInvalidos_RetornarErrorMessageComHttpStatus400() {

        var dtoIn = CriadorDeBuilders.gerarCarteiraDepositarDtoInBuilder()
            .id(20L)
            .saldo(null)
            .build();

        var resposta = this.webTestClient.put()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarCarteiraDepositarDtoInBuilder()
            .id(20L)
            .saldo(BigDecimal.valueOf(-12))
            .build();

        resposta = this.webTestClient.put()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);

        dtoIn = CriadorDeBuilders.gerarCarteiraDepositarDtoInBuilder()
            .id(20L)
            .saldo(BigDecimal.ZERO)
            .build();

        resposta = this.webTestClient.put()
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
}

