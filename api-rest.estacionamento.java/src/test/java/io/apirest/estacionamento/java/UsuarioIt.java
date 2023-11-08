package io.apirest.estacionamento.java;

import io.apirest.estacionamento.java.web.dto.UsuarioCreateDto;
import io.apirest.estacionamento.java.web.dto.UsuarioResponseDto;
import io.apirest.estacionamento.java.web.dto.UsuarioSenhaDto;
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
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioIt {

    private static final String CAMINHO = "/api/v1/usuarios";

    @Autowired
    WebTestClient testClient;

    @Test
    @Order(1)
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
    @Order(2)
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
    @Order(3)
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
    @Order(4)
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
    @Order(10)
    public void buscarUsuario_ComIdExistente_RetornarUsuarioComStatus200() {

        var responseBody = this.testClient.get()
            .uri(CAMINHO.concat("/100"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "bob@email.com", "123456"))
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

        var responseBody2 = this.testClient.get()
            .uri(CAMINHO.concat("/101"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "fowler@email.com", "123456"))
            .exchange()
            .expectStatus().isOk()
            .expectBody(UsuarioResponseDto.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody2.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody2.getUsername()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody2.getRole()).isNotNull();

        org.assertj.core.api.Assertions.assertThat(responseBody2.getId()).isEqualTo(101);
        org.assertj.core.api.Assertions.assertThat(responseBody2.getUsername()).isEqualTo("fowler@email.com");
        org.assertj.core.api.Assertions.assertThat(responseBody2.getRole()).isEqualTo("CLIENTE");
    }

    @Test
    @Order(11)
    public void buscarUsuario_ComIdInexistente_RetornarErrorMessageComStatus404() {

        var responseBody = this.testClient.get()
            .uri(CAMINHO.concat("/0"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "bob@email.com", "123456"))
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
    }

    @Test
    @Order(12)
    public void buscarUsuario_ComUsuarioClienteBuscandoOutroCliente_RetornarErrorMessageComStatus403() {

        var responseBody = this.testClient.get()
            .uri(CAMINHO.concat("/101"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "beck@email.com", "123456"))
            .exchange()
            .expectStatus().isForbidden()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }

    @Test
    @Order(13)
    public void buscarUsuario_ComUsuarioAdminBuscandoCliente_RetornarUsuarioComStatus200() {

        var responseBody = this.testClient.get()
            .uri(CAMINHO.concat("/102"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "bob@email.com", "123456"))
            .exchange()
            .expectStatus().isOk()
            .expectBody(UsuarioResponseDto.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isNotNull();

        org.assertj.core.api.Assertions.assertThat(responseBody.getId()).isEqualTo(102);
        org.assertj.core.api.Assertions.assertThat(responseBody.getUsername()).isEqualTo("beck@email.com");
        org.assertj.core.api.Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
    }

    @Test
    @Order(20)
    public void editarSenha_ComDadosValidos_RetornarStatus204() {

        // Teste ADMIN
        this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "bob@email.com", "123456"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "654321", "654321"))
            .exchange()
            .expectStatus().isNoContent();

        // Teste CLIENTE
        this.testClient.patch()
            .uri(CAMINHO.concat("/101"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "fowler@email.com", "123456"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "123456", "123456"))
            .exchange()
            .expectStatus().isNoContent();
    }

    @Test
    @Order(21)
    public void editarSenha_ComDadosInvalidos_RetornarErrorMessageStatus403() {

        // Teste ADMIN
        var responseBody = this.testClient.patch()
            .uri(CAMINHO.concat("/101"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "bob@email.com", "123456"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "123456", "123456"))
            .exchange()
            .expectStatus().isForbidden()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);

        // Teste CLIENTE
        var responseBody2 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .headers(JwtAuthentication.getHeaderAuthorization(this.testClient, "fowler@email.com", "123456"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "123456", "123456"))
            .exchange()
            .expectStatus().isForbidden()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(403);
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

    @Test
    public void editarSenha_ComSenhaInvalida_RetornarErrorMessageStatus400() {

        var responseBody = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("111111", "654321", "654321"))
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);

        var responseBody2 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "111111", "654321"))
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody2).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody2.getStatus()).isEqualTo(400);

        var responseBody3 = this.testClient.patch()
            .uri(CAMINHO.concat("/100"))
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new UsuarioSenhaDto("123456", "654321", "111111"))
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody3).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody3.getStatus()).isEqualTo(400);
    }

    @Test
    public void listarUsuarios_SemParametros_RetornarListaDeUsuariosComStatus200() {

        var responseBody = this.testClient.get()
            .uri(CAMINHO)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(UsuarioResponseDto.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(3);
        org.assertj.core.api.Assertions.assertThat(responseBody.get(0).getId()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.get(0).getUsername()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(responseBody.get(0).getRole()).isNotNull();
    }
}

