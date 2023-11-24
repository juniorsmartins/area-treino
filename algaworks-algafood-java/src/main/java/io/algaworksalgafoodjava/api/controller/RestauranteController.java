package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.service.CadastroRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final CadastroRestauranteService cadastroRestauranteService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Restaurante>> listar() {

        var resposta = this.cadastroRestauranteService.listar();

        return ResponseEntity
            .ok()
            .body(resposta);
    }
}

