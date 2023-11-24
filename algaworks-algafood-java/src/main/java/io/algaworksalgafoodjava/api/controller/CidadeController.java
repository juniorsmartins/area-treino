package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.model.Cidade;
import io.algaworksalgafoodjava.domain.service.CadastroCidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}

