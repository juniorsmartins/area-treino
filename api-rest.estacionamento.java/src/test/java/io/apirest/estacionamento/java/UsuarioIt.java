package io.apirest.estacionamento.java;

import io.apirest.estacionamento.java.web.dto.UsuarioCreateDto;
import io.apirest.estacionamento.java.web.dto.UsuarioResponseDto;
import io.apirest.estacionamento.java.web.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UsuarioIt {

    private static final String CAMINHO = "/api/v1/usuarios";

    @Autowired
    WebTestClient testClient;

    @Test
    public void createUsuario_ComUsernameAndPasswordValidos_RetornarUsuarioCriadoComStatus201() {

        var responseBody = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("tody@email.com", "123456"))
            .exchange()
            .expectStatus().isCreated()
            .expectBody(UsuarioResponseDto.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isNotNull();

        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("tody@email.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
    }

    @Test
    public void createUsuario_ComUsernameInvalido_RetornarErrorMessageComStatus422() {

        var responseBody = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        var responseBody2 = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("teste@", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(422);

        var responseBody3 = this.testClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioCreateDto("teste@email", "123456"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody3.getStatus()).isEqualTo(422);
    }
}

