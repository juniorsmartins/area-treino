package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.api.wrapper.CozinhaXmlWrapper;
import io.algaworksalgafoodjava.domain.model.Cozinha;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cozinha> listar() {
        return this.cozinhaRepository.listar();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    public CozinhaXmlWrapper listarXml() {
        return new CozinhaXmlWrapper(this.cozinhaRepository.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable(name = "id") final Long id) {

        var cozinha = this.cozinhaRepository.buscar(id);

        if (ObjectUtils.isEmpty(cozinha)) {
            return ResponseEntity
                .notFound()
                .build();
        }

        return ResponseEntity
            .ok()
            .body(cozinha);
    }
}

