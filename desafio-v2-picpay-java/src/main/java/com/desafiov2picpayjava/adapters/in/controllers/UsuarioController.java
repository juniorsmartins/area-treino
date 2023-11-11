package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioBuscarDtoOut;
import com.desafiov2picpayjava.adapters.in.dtos.UsuarioCadastrarDtoIn;
import com.desafiov2picpayjava.adapters.in.dtos.UsuarioCadastrarDtoOut;
import com.desafiov2picpayjava.adapters.in.mappers.UsuarioBuscarDtoOutMapper;
import com.desafiov2picpayjava.adapters.in.mappers.UsuarioCadastrarDtoInMapper;
import com.desafiov2picpayjava.adapters.in.mappers.UsuarioCadastrarDtoOutMapper;
import com.desafiov2picpayjava.application.ports.in.UsuarioBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.in.UsuarioBuscarTodosInputPort;
import com.desafiov2picpayjava.application.ports.in.UsuarioCadastrarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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

    private final UsuarioBuscarTodosInputPort usuarioBuscarTodosInputPort;

    private final UsuarioCadastrarDtoInMapper usuarioCadastrarDtoInMapper;

    private final UsuarioCadastrarDtoOutMapper usuarioCadastrarDtoOutMapper;

    private final UsuarioBuscarDtoOutMapper usuarioBuscarDtoOutMapper;

    @PostMapping
    public ResponseEntity<UsuarioCadastrarDtoOut> cadastrar(@RequestBody @Valid UsuarioCadastrarDtoIn dtoIn) {

        this.logger.info("Controller - recebida requisição para cadastrar Usuário.");

        var dtoOut = Optional.of(dtoIn)
            .map(this.usuarioCadastrarDtoInMapper::toUsuario)
            .map(this.usuarioCadastrarInputPort::cadastrar)
            .map(this.usuarioCadastrarDtoOutMapper::toUsuarioDtoOut)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Controller - concluído com sucesso cadastro de Usuário.");

        return ResponseEntity
            .created(URI.create("/api/v1/usuarios/" + dtoOut.id()))
            .body(dtoOut);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioBuscarDtoOut> buscarPorId(@PathVariable(name = "id") final Long id) {

        this.logger.info("Controller - recebida requisição para buscar Usuário por id.");

        var dtoOut = Optional.of(id)
            .map(this.usuarioBuscarPorIdInputPort::buscarPorId)
            .map(this.usuarioBuscarDtoOutMapper::toUsuarioBuscarDtoOut)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Controller - concluído com sucesso busca de Usuário por id.");

        return ResponseEntity
            .ok()
            .body(dtoOut);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioBuscarDtoOut>> buscarTodos() {

        this.logger.info("Controller - recebida requisição para buscar todos os Usuários.");

        var usuarios = this.usuarioBuscarTodosInputPort.buscarTodos()
            .stream()
            .map(this.usuarioBuscarDtoOutMapper::toUsuarioBuscarDtoOut)
            .toList();

        this.logger.info("Controller - concluído com sucesso buscar todos os Usuários.");

        return ResponseEntity
            .ok()
            .body(usuarios);
    }
}

