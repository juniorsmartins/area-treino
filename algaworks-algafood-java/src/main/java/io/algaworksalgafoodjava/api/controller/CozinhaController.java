package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.api.wrapper.CozinhaXmlWrapper;
import io.algaworksalgafoodjava.domain.model.Cozinha;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import io.algaworksalgafoodjava.domain.service.CadastroCozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
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

    private final CadastroCozinhaService cadastroCozinhaService;

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

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        var cozinhaSalva = this.cadastroCozinhaService.salvar(cozinha);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cozinhaSalva);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable(name = "id") final Long id,
                                             @RequestBody Cozinha cozinha) {

        var cozinhaEncontrada = this.cozinhaRepository.buscar(id);

        if (ObjectUtils.isEmpty(cozinhaEncontrada)) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cozinha, cozinhaEncontrada, "id");
        var cozinhaAtualizada = this.cozinhaRepository.salvar(cozinhaEncontrada);

        return ResponseEntity
            .ok()
            .body(cozinhaAtualizada);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable(name = "id") final Long id) {
        var cozinha = this.cozinhaRepository.buscar(id);

        if (ObjectUtils.isEmpty(cozinha)) {
            return ResponseEntity
                .notFound()
                .build();
        }

        try {
            this.cozinhaRepository.remover(cozinha);

        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .build();
        }

        return ResponseEntity
            .noContent()
            .build();
    }
}

