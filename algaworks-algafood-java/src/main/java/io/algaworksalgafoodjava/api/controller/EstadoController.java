package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.exception.EntidadeEmUsoException;
import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Estado;
import io.algaworksalgafoodjava.domain.service.CadastroEstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final CadastroEstadoService cadastroEstadoService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Estado>> listar() {

        var resposta = this.cadastroEstadoService.listar();

        return ResponseEntity
            .ok()
            .body(resposta);
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Estado> buscar(@PathVariable(name = "id") final Long id) {

        var resposta = this.cadastroEstadoService.buscar(id);

        if(ObjectUtils.isEmpty(resposta)) {
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
    public ResponseEntity<Object> adicionar(@RequestBody Estado estado) {

        var resposta = this.cadastroEstadoService.salvar(estado);

        return ResponseEntity
            .created(URI.create("/api/v1/estados/" + resposta.getId()))
            .body(resposta);
    }

    @PutMapping(path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Estado> atualizar(@PathVariable(name = "id") final Long id,
                                            @RequestBody Estado estado) {
        estado.setId(id);

        try {
            estado = this.cadastroEstadoService.atualizar(estado);

            return ResponseEntity
                .ok()
                .body(estado);

        } catch (EntidadeNaoEncontradaException ex) {
            return ResponseEntity
                .notFound()
                .build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable(name = "id") final Long id) {

        try {
            this.cadastroEstadoService.excluir(id);

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

