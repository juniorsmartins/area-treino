package io.apirest.estacionamento.java;

import io.apirest.estacionamento.java.web.dto.UsuarioCreateDto;
import io.apirest.estacionamento.java.web.dto.UsuarioResponseDto;
import io.apirest.estacionamento.java.web.dto.UsuarioSenhaDto;
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
    public void createUsuario_ComUsernameRepetido_RetornarErrorMessageComStatus409() {

        var responseBody = this.testClient.post()
                .uri(CAMINHO)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("fowler@email.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
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

        var responseBody4 = this.testClient.post()
                .uri(CAMINHO)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("teste@email.", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody4).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody4.getStatus()).isEqualTo(422);
    }

    @Test
    public void createUsuario_ComPasswordInvalido_RetornarErrorMessageComStatus422() {

        var responseBody = this.testClient.post()
                .uri(CAMINHO)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("teste@email.com", "12345"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        var responseBody2 = this.testClient.post()
                .uri(CAMINHO)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("teste@email.com", "1234567"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(422);

        var responseBody3 = this.testClient.post()
                .uri(CAMINHO)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("teste@email.com", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody3.getStatus()).isEqualTo(422);
    }

    @Test
    public void buscarUsuario_ComIdExistente_RetornarUsuarioComStatus200() {

        var responseBody = this.testClient.get()
            .uri(CAMINHO.concat("/100"))
            .exchange()
            .expectStatus().isOk()
            .expectBody(UsuarioResponseDto.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isNotNull();

        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(100);
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("bob@email.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("ADMIN");
    }

    @Test
    public void buscarUsuario_ComIdInexistente_RetornarErrorMessageComStatus404() {

        var responseBody = this.testClient.get()
            .uri(CAMINHO.concat("/0"))
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }

    @Test
    public void editarSenha_ComDadosValidos_RetornarStatus204() {

        this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "654321", "654321"))
            .exchange()
            .expectStatus().isNoContent();
    }

    @Test
    public void editarSenha_ComDadosInvalidos_RetornarErrorMessageStatus404() {

        var responseBody = this.testClient.patch()
            .uri(CAMINHO.concat("/0"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "654321", "654321"))
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }

    @Test
    public void editarSenha_ComCamposInvalidos_RetornarErrorMessageStatus422() {

        var responseBody = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("", "654321", "654321"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

        var responseBody2 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123", "654321", "654321"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(422);

        var responseBody3 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456789", "654321", "654321"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody3.getStatus()).isEqualTo(422);

        var responseBody4 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "", "654321"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody4).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody4.getStatus()).isEqualTo(422);

        var responseBody5 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "654", "654321"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody5).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody5.getStatus()).isEqualTo(422);

        var responseBody6 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456789", "987654321", "654321"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody6).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody6.getStatus()).isEqualTo(422);

        var responseBody7 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "654321", ""))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody7).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody7.getStatus()).isEqualTo(422);

        var responseBody8 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "654321", "654"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody8).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody8.getStatus()).isEqualTo(422);

        var responseBody9 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "654321", "987654321"))
            .exchange()
            .expectStatus().isEqualTo(422)
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody9).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody9.getStatus()).isEqualTo(422);
    }
}

