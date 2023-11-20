package io.apirest.estacionamento.java;

import io.apirest.estacionamento.java.web.dto.ClienteCreateDto;
import io.apirest.estacionamento.java.web.dto.ClienteResponseDto;
import io.apirest.estacionamento.java.web.exception.ErrorMessage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/clientes/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes/clientes-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteIt {

    private static final String CAMINHO = "/api/v1/clientes";

    @Autowired
    WebTestClient testClient;

    @Test
    @Order(1)
    void criarCliente_ComDadosValidos_RetornarClienteComStatus201() {

        var resposta = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "toby@email.com", "123456"))
            .bodyValue(new ClienteCreateDto("Tobias Ferreira", "85188387034"))
            .exchange()
            .expectStatus().isCreated()
            .expectBody(ClienteResponseDto.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getNome()).isEqualTo("Tobias Ferreira");
        org.assertj.core.api.Assertions.assertThat(resposta.getCpf()).isEqualTo("85188387034");
    }

    @Test
    @Order(2)
    void criarCliente_ComCpfJaCadastrado_RetornarErrorMessageComStatus409() {

        var resposta = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "toby@email.com", "123456"))
            .bodyValue(new ClienteCreateDto("Tobias Ferreira", "78149242007"))
            .exchange()
            .expectStatus().isEqualTo(409)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(409);
    }

    @Test
    @Order(3)
    void criarCliente_ComDadosInvalidos_RetornarErrorMessageComStatus422() {

        var resposta = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "toby@email.com", "123456"))
            .bodyValue(new ClienteCreateDto(" ", "78149242007"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(422);

        resposta = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "toby@email.com", "123456"))
            .bodyValue(new ClienteCreateDto("Tobias Ferreira", " "))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(422);

        resposta = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "toby@email.com", "123456"))
            .bodyValue(new ClienteCreateDto("Tobi", "78149242007"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(422);

        resposta = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "toby@email.com", "123456"))
            .bodyValue(new ClienteCreateDto("Tobias Ferreira", "11111111111"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(422);

        resposta = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "toby@email.com", "123456"))
            .bodyValue(new ClienteCreateDto("Tobias Ferreira", "781.492.420-07"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(422);
    }

    @Test
    @Order(4)
    void criarCliente_ComUsuarioNaoPermitido_RetornarErrorMessageComStatus403() {

        var resposta = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "bob@email.com", "123456"))
            .bodyValue(new ClienteCreateDto("Tobias Ferreira", "78149242007"))
            .exchange()
            .expectStatus().isForbidden()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(403);
    }

    @Test
    @Order(5)
    void buscarCliente_ComIdExistentePeloAdmin_RetornarClienteComStatus200() {

        var resposta = this.testClient.get()
            .uri("/api/v1/clientes/21")
            .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bob@email.com", "123456"))
            .exchange()
            .expectStatus().isOk()
            .expectBody(ClienteResponseDto.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getId()).isEqualTo(21);
    }
}

