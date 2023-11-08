package io.apirest.estacionamento.java;

import io.apirest.estacionamento.java.web.dto.ClienteCreateDto;
import io.apirest.estacionamento.java.web.dto.ClienteResponseDto;
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
public class ClienteIt {

    private static final String CAMINHO = "/api/v1/clientes";

    @Autowired
    WebTestClient testClient;

    @Test
    @Order(1)
    public void criarCliente_ComDadosValidos_RetornarClienteComStatus201() {

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
        org.assertj.core.api.Assertions.assertThat(resposta.getNome()).isNotNull();
        org.assertj.core.api.Assertions.assertThat(resposta.getCpf()).isNotNull();

        org.assertj.core.api.Assertions.assertThat(resposta.getNome()).isEqualTo("Tobias Ferreira");
        org.assertj.core.api.Assertions.assertThat(resposta.getCpf()).isEqualTo("85188387034");
    }
}

