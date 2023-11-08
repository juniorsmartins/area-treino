package io.apirest.estacionamento.java;

import io.apirest.estacionamento.java.security.dto.UsuarioLoginDto;
import io.apirest.estacionamento.java.security.jwt.JwtToken;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Gera porta randômica para cada teste - para não haver conflitos de porta
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD) // Adiciona dados antes de cada teste
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD) // Apaga os dados após cada teste
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutenticacaoIt {

    private static final String CAMINHO = "/api/v1/auth";

    @Autowired
    WebTestClient testClient; // Objeto usado para trabalhar com os métodos de teste

    @Test
    @Order(1)
    public void autenticar_ComCredenciaisValidas_RetornarTokenComStatus200() {

        var jwtToken = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("fowler@email.com", "123456"))
            .exchange()
            .expectStatus().isOk()
            .expectBody(JwtToken.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(jwtToken).isNotNull();
    }

    @Test
    @Order(2)
    public void autenticar_ComCredenciaisInvalidas_RetornarErrorMessageComStatus400() {

        var response = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("invalido@email.com", "123456"))
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(400);

        var response2 = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("fowler@email.com", "000000"))
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response2.getStatus()).isEqualTo(400);
    }

    @Test
    @Order(3)
    public void autenticar_ComUsernameInvalido_RetornarErrorMessageComStatus422() {

        var response = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        var response2 = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto(" ", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response2.getStatus()).isEqualTo(422);

        var response3 = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("@email.com", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response3).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response3.getStatus()).isEqualTo(422);
    }

    @Test
    @Order(4)
    public void autenticar_ComSenhaInvalida_RetornarErrorMessageComStatus422() {

        var response = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("fowler@email.com", ""))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response.getStatus()).isEqualTo(422);

        var response2 = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("fowler@email.com", " "))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response2.getStatus()).isEqualTo(422);

        var response3 = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("fowler@email.com", "123"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response3).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response3.getStatus()).isEqualTo(422);

        var response4 = testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioLoginDto("fowler@email.com", "123456789"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(response4).isNotNull();
        org.assertj.core.api.Assertions.assertThat(response4.getStatus()).isEqualTo(422);
    }
}

