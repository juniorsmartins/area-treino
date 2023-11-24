package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.model.Cidade;
import io.algaworksalgafoodjava.domain.service.CadastroCidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

