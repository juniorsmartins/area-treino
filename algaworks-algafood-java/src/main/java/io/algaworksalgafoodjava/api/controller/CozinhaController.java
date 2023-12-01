package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.api.wrapper.CozinhaXmlWrapper;
import io.algaworksalgafoodjava.domain.exception.EntidadeEmUsoException;
import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Cozinha;
import io.algaworksalgafoodjava.domain.service.CadastroCozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {

    private final CadastroCozinhaService cadastroCozinhaService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Cozinha> listar() {
        return this.cadastroCozinhaService.listar();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
    public CozinhaXmlWrapper listarXml() {
        return new CozinhaXmlWrapper(this.cadastroCozinhaService.listar());
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Cozinha> buscar(@PathVariable(name = "id") final Long id) {

        return this.cadastroCozinhaService.buscar(id)
            .map(ResponseEntity.ok()::body)
            .orElseGet(ResponseEntity.notFound()::build);
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

    @PutMapping(path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Cozinha> atualizar(@PathVariable(name = "id") final Long id,
                                             @RequestBody Cozinha cozinha) {

        return this.cadastroCozinhaService.buscar(id)
            .map(cozinhaEncontrada -> {
                BeanUtils.copyProperties(cozinha, cozinhaEncontrada, "id");
                var cozinhaAtualizada = this.cadastroCozinhaService.salvar(cozinhaEncontrada);

                return ResponseEntity
                    .ok()
                    .body(cozinhaAtualizada);

            })
            .orElseGet(ResponseEntity.notFound()::build);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable(name = "id") final Long id) {

        try {
            this.cadastroCozinhaService.excluir(id);

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

