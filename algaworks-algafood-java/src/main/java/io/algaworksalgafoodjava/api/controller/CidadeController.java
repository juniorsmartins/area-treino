package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Cidade;
import io.algaworksalgafoodjava.domain.service.CadastroCidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CadastroCidadeService cadastroCidadeService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Cidade>> listar() {

        var resposta = this.cadastroCidadeService.listar();

        return ResponseEntity
            .ok()
            .body(resposta);
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Cidade> buscar(@PathVariable(name = "id") final Long id) {

        var resposta = this.cadastroCidadeService.buscar(id);

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
    public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {

        try {
            cidade = this.cadastroCidadeService.salvar(cidade);

            return ResponseEntity
                .created(URI.create("/api/v1/cidades/" + cidade.getId()))
                .body(cidade);

        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                .notFound()
                .build();
        }
    }

    @PutMapping(path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<?> atualizar(@PathVariable(name = "id") final Long id,
                                            @RequestBody Cidade cidade) {
        cidade.setId(id);

        try {
            cidade = this.cadastroCidadeService.atualizar(cidade);

            return ResponseEntity
                .ok()
                .body(cidade);

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
}

