package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.exception.EntidadeEmUsoException;
import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.service.CadastroRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

        var resposta = this.cadastroRestauranteService.buscar(id);

        if (ObjectUtils.isEmpty(resposta)) {
            return ResponseEntity
                .notFound()
                .build();
        }

        return ResponseEntity
            .ok()
            .body(resposta);
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Object> adicionar(@RequestBody Restaurante restaurante) {

        try {
            restaurante = this.cadastroRestauranteService.salvar(restaurante);

            return ResponseEntity
                .created(URI.create("/api/v1/restaurantes/" + restaurante.getId()))
                .body(restaurante);

        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<?> atualizar(@PathVariable(name = "id") final Long id,
                                                 @RequestBody Restaurante restaurante) {
        restaurante.setId(id);

        try {
            var resposta = this.cadastroRestauranteService.atualizar(restaurante);

            return ResponseEntity
                .ok()
                .body(resposta);

        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                .notFound()
                .build();

        } catch (IllegalArgumentException ex) {
            return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable(name = "id") final Long id) {

        try {
            this.cadastroRestauranteService.excluir(id);

            return ResponseEntity
                .noContent()
                .build();

        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                .notFound()
                .build();

        } catch (EntidadeEmUsoException ex) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .build();
        }
    }
}

