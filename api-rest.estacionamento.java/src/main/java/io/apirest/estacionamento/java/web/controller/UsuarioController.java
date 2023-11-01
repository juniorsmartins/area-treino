package io.apirest.estacionamento.java.web.controller;

import io.apirest.estacionamento.java.entity.Usuario;
import io.apirest.estacionamento.java.service.UsuarioService;
import io.apirest.estacionamento.java.web.dto.UsuarioCreateDto;
import io.apirest.estacionamento.java.web.dto.UsuarioResponseDto;
import io.apirest.estacionamento.java.web.dto.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody UsuarioCreateDto createDto) {


        var user = this.usuarioService.salvar(UsuarioMapper.toUsuario(createDto));

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UsuarioMapper.toUsuarioResponseDto(user));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable(name = "id") final Long id) {

        var user = this.usuarioService.buscarPorId(id);

        return ResponseEntity
            .ok()
            .body(user);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Usuario> updatePassword(@PathVariable(name = "id") final Long id, @RequestBody Usuario usuario) {

        var user = this.usuarioService.editarSenha(id, usuario.getPassword());

        return ResponseEntity
            .ok()
            .body(user);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {

        var users = this.usuarioService.buscarTodos();

        return ResponseEntity
            .ok()
            .body(users);
    }
}
