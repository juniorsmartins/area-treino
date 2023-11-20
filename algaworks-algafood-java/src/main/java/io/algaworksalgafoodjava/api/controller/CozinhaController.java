package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.api.wrapper.CozinhaXmlWrapper;
import io.algaworksalgafoodjava.domain.model.Cozinha;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    public CozinhaXmlWrapper listarXml() {
        return new CozinhaXmlWrapper(this.cozinhaRepository.listar());
    }

    @GetMapping(path = "/{id}")
    public Cozinha buscar(@PathVariable(name = "id") final Long id) {
        return this.cozinhaRepository.buscar(id);
    }
}

