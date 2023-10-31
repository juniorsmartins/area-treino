package io.apirest.estacionamento.java.web;

import io.apirest.estacionamento.java.entity.Usuario;
import io.apirest.estacionamento.java.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {

        var user = this.usuarioService.salvar(usuario);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(user);
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
}
