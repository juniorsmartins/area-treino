package io.apirest.estacionamento.java.web.controller;

import io.apirest.estacionamento.java.service.UsuarioService;
import io.apirest.estacionamento.java.web.dto.UsuarioCreateDto;
import io.apirest.estacionamento.java.web.dto.UsuarioResponseDto;
import io.apirest.estacionamento.java.web.dto.UsuarioSenhaDto;
import io.apirest.estacionamento.java.web.dto.mapper.UsuarioMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody @Valid UsuarioCreateDto createDto) {

        var user = this.usuarioService.salvar(UsuarioMapper.toUsuario(createDto));

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UsuarioMapper.toUsuarioResponseDto(user));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable(name = "id") final Long id) {

        var user = this.usuarioService.buscarPorId(id);

        return ResponseEntity
            .ok()
            .body(UsuarioMapper.toUsuarioResponseDto(user));
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable(name = "id") final Long id, @RequestBody UsuarioSenhaDto senhaDto) {

        this.usuarioService
            .editarSenha(id, senhaDto.getSenhaAtual(), senhaDto.getNovaSenha(), senhaDto.getConfirmaSenha());

        return ResponseEntity
            .noContent()
            .build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {

        var users = this.usuarioService.buscarTodos();

        return ResponseEntity
            .ok()
            .body(UsuarioMapper.toListUsuarioResponseDto(users));
    }
}
