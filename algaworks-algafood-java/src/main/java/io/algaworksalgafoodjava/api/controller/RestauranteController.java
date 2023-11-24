package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.service.CadastroRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Restaurante> buscar(@PathVariable(name = "id") final Long id) {

        var restaurante = this.cadastroRestauranteService.buscar(id);

        if (ObjectUtils.isEmpty(restaurante)) {
            return ResponseEntity
                .notFound()
                .build();
        }

        return ResponseEntity
            .ok()
            .body(restaurante);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Restaurante> adicionar(@RequestBody Restaurante restaurante) {

        try {
            restaurante = this.cadastroRestauranteService.salvar(restaurante);

            return ResponseEntity
                .created(URI.create("/api/v1/restaurantes/" + restaurante.getId()))
                .body(restaurante);

        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                .badRequest()
                .build();
        }
    }
}

