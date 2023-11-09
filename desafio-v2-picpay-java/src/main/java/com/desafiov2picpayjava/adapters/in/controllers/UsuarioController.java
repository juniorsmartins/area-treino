package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioDtoIn;
import com.desafiov2picpayjava.adapters.in.dtos.UsuarioDtoOut;
import com.desafiov2picpayjava.adapters.in.mappers.UsuarioDtoInMapper;
import com.desafiov2picpayjava.adapters.in.mappers.UsuarioDtoOutMapper;
import com.desafiov2picpayjava.application.ports.in.UsuarioCadastrarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioCadastrarInputPort usuarioCadastrarInputPort;

    private final UsuarioDtoInMapper usuarioDtoInMapper;

    private final UsuarioDtoOutMapper usuarioDtoOutMapper;

    @PostMapping
    public ResponseEntity<UsuarioDtoOut> cadastrar(@RequestBody @Valid UsuarioDtoIn dtoIn) {

        var dtoOut = Optional.of(dtoIn)
            .map(this.usuarioDtoInMapper::toUsuario)
            .map(this.usuarioCadastrarInputPort::cadastrar)
            .map(this.usuarioDtoOutMapper::toUsuarioDtoOut)
            .orElseThrow(NoSuchElementException::new);

        return ResponseEntity
            .created(URI.create("/api/v1/usuarios/" + dtoOut.id()))
            .body(dtoOut);
    }
}

