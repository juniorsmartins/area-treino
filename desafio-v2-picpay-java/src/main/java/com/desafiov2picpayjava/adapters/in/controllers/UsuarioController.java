package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioCadastrarDtoIn;
import com.desafiov2picpayjava.adapters.in.dtos.UsuarioDtoOut;
import com.desafiov2picpayjava.adapters.in.mappers.UsuarioDtoInMapper;
import com.desafiov2picpayjava.adapters.in.mappers.UsuarioDtoOutMapper;
import com.desafiov2picpayjava.application.ports.in.UsuarioBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.in.UsuarioCadastrarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    private final UsuarioCadastrarInputPort usuarioCadastrarInputPort;

    private final UsuarioBuscarPorIdInputPort usuarioBuscarPorIdInputPort;

    private final UsuarioDtoInMapper usuarioDtoInMapper;

    private final UsuarioDtoOutMapper usuarioDtoOutMapper;

    @PostMapping
    public ResponseEntity<UsuarioDtoOut> cadastrar(@RequestBody @Valid UsuarioCadastrarDtoIn dtoIn) {

        this.logger.info("Controller - recebida requisição para cadastrar Usuário.");

        var dtoOut = Optional.of(dtoIn)
            .map(this.usuarioDtoInMapper::toUsuario)
            .map(this.usuarioCadastrarInputPort::cadastrar)
            .map(this.usuarioDtoOutMapper::toUsuarioDtoOut)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Controller - concluído com sucesso cadastro de Usuário.");

        return ResponseEntity
            .created(URI.create("/api/v1/usuarios/" + dtoOut.id()))
            .body(dtoOut);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDtoOut> buscarPorId(@PathVariable(name = "id") final Long id) {

        this.logger.info("Controller - recebida requisição para buscar Usuário por id.");

        var dtoOut = Optional.of(id)
            .map(this.usuarioBuscarPorIdInputPort::buscarPorId)
            .map(this.usuarioDtoOutMapper::toUsuarioDtoOut)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Controller - concluído com sucesso busca de Usuário por id.");

        return ResponseEntity
            .ok()
            .body(dtoOut);
    }
}

