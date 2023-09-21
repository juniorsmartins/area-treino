package io.rinhabackendjava.adapters.in.controller;

import io.rinhabackendjava.adapters.in.controller.mapper.PessoaRequestMapper;
import io.rinhabackendjava.adapters.in.controller.mapper.PessoaResponseMapper;
import io.rinhabackendjava.adapters.in.controller.request.PessoaRequest;
import io.rinhabackendjava.adapters.in.controller.response.PessoaResponse;
import io.rinhabackendjava.application.ports.in.PessoaCreateInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaCreateInputPort pessoaCreateInputPort;

    @Autowired
    private PessoaRequestMapper pessoaRequestMapper;

    @Autowired
    private PessoaResponseMapper pessoaResponseMapper;

    @PostMapping
    public PessoaResponse criar(@RequestBody @Valid PessoaRequest pessoaRequest) {

        return Optional.of(pessoaRequest)
                .map(this.pessoaRequestMapper::toPessoaBusiness)
                .map(this.pessoaCreateInputPort::create)
                .map(this.pessoaResponseMapper::toPessoaResponse)
                .orElseThrow();
    }
}

