package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioCadastrarDtoOut;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControllerIntegrationTest {

    private static final String CAMINHO = "/api/v1/usuarios";

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(1)
    public void cadastrarUsuario_ComDadosValidos_RetornarUsuarioDtoOutComHttpStatus201() {

        var dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder().build();

        var resposta = this.webTestClient.post()
            .uri(CAMINHO)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(dtoIn)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(UsuarioCadastrarDtoOut.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.nome()).isEqualToIgnoringCase(dtoIn.nome());
        org.assertj.core.api.Assertions.assertThat(resposta.documento()).isEqualTo(dtoIn.documento());
        org.assertj.core.api.Assertions.assertThat(resposta.email()).isEqualTo(dtoIn.email());
        org.assertj.core.api.Assertions.assertThat(resposta.senha()).isEqualTo(dtoIn.senha());
        org.assertj.core.api.Assertions.assertThat(resposta.tipo()).isEqualToIgnoringCase(dtoIn.tipo());
    }

    @Test
    @Order(2)
    public void cadastrarUsuario_ComNomeInvalido_RetornarErrorMessageComHttpStatus400() {

        var dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .nome("Arnaldo Antunes da Costa Serrana do Rio Azul na Descida da Montanha dos Pampas Verdes e Úmidos do Sul")
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .nome(null)
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .nome("   ")
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
    public void cadastrarUsuario_ComDocumentoInvalido_RetornarErrorMessageComHttpStatus400() {

//        var dtoIn = CriadorDeBuilders.gerarUsuarioDtoInBuilder()
//                .nome("Arnaldo Antunes da Costa Serrana do Rio Azul na Descida da Montanha dos Pampas Verdes e Úmidos do Sul")
//                .build();
//
//        var resposta = this.webTestClient.post()
//                .uri(CAMINHO)
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(dtoIn)
//                .exchange()
//                .expectStatus().isBadRequest()
//                .expectBody(ErrorMessage.class)
//                .returnResult().getResponseBody();
//
//        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
//        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(400);
    }

    @Test
    @Order(4)
    public void cadastrarUsuario_ComEmailInvalido_RetornarErrorMessageComHttpStatus400() {

        var dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .email("avemariacheiadegracaseunomeeconvostosejafeitasuavontadeaquinaterracomonoseuamemamemamemamem@email.com")
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .email(null)
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .email("   ")
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .email("teste@email")
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .email("@email.com")
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
    @Order(5)
    public void cadastrarUsuario_ComSenhaInvalida_RetornarErrorMessageComHttpStatus400() {

        var dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .senha("123456789abcdefghi10qwertxpto12")
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .senha("123")
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .senha(null)
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .senha("    ")
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
    @Order(2)
    public void cadastrarUsuario_ComTipoInvalido_RetornarErrorMessageComHttpStatus400() {

        var dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .tipo(null)
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

        dtoIn = CriadorDeBuilders.gerarUsuarioCadastrarDtoInBuilder()
                .nome("   ")
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
    @Order(10)
    public void buscarUsuarioPorId_ComIdExistente_RetornarUsuarioDtoOutComHttpStatus200() {

        var resposta = this.webTestClient.get()
            .uri(CAMINHO.concat("/10"))
            .exchange()
            .expectStatus().isOk()
            .expectBody(UsuarioCadastrarDtoOut.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.id()).isEqualTo(10);
        org.assertj.core.api.Assertions.assertThat(resposta.nome()).isEqualTo("Jeff Sutherland");
        org.assertj.core.api.Assertions.assertThat(resposta.documento()).isEqualTo("73249716014");
        org.assertj.core.api.Assertions.assertThat(resposta.email()).isEqualTo("jeff@email.com");
        org.assertj.core.api.Assertions.assertThat(resposta.senha()).isEqualTo("123456");
        org.assertj.core.api.Assertions.assertThat(resposta.tipo()).isEqualTo("LOJISTA");
    }

    @Test
    @Order(11)
    public void buscarUsuarioPorId_ComIdInexistente_RetornarErrorMessageComHttpStatus404() {

        var resposta = this.webTestClient.get()
            .uri(CAMINHO.concat("/0"))
            .exchange()
            .expectStatus().isNotFound()
            .expectBody(ErrorMessage.class)
            .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat(resposta).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getStatus()).isEqualTo(404);
    }
}

