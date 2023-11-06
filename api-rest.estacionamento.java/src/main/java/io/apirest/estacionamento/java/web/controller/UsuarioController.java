package io.apirest.estacionamento.java.web.controller;

import io.apirest.estacionamento.java.service.UsuarioService;
import io.apirest.estacionamento.java.web.dto.UsuarioCreateDto;
import io.apirest.estacionamento.java.web.dto.UsuarioResponseDto;
import io.apirest.estacionamento.java.web.dto.UsuarioSenhaDto;
import io.apirest.estacionamento.java.web.dto.mapper.UsuarioMapper;
import io.apirest.estacionamento.java.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "Contém todos os recursos de Usuários (cadastro, edição e leitura).")
@RestController
@RequestMapping(path = "/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Criar Usuário.", description = "Recurso para criar um novo Usuário.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Usuário com email já cadastrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada inválidos.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    ErrorMessage.class))),
        })
    public ResponseEntity<UsuarioResponseDto> create(@RequestBody @Valid UsuarioCreateDto createDto) {

        var user = this.usuarioService.salvar(UsuarioMapper.toUsuario(createDto));

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UsuarioMapper.toUsuarioResponseDto(user));
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('CLIENTE') AND #id == authentication.principal.id)")
    @Operation(summary = "Recuperar Usuário por id.", description = "Recurso para recuperar um Usuário por id.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    ErrorMessage.class)))
        })
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable(name = "id") final Long id) {

        var user = this.usuarioService.buscarPorId(id);

        return ResponseEntity
            .ok()
            .body(UsuarioMapper.toUsuarioResponseDto(user));
    }

    @PatchMapping(path = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE') AND (#id == authentication.principal.id)")
    @Operation(summary = "Atualizar senha.", description = "Recurso para atualizar a senha de um Usuário.",
        responses = {
            @ApiResponse(responseCode = "204", description = "Recurso atualizado com sucesso.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    Void.class))),
            @ApiResponse(responseCode = "400", description = "Senha não confere.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    ErrorMessage.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Recurso com campos inválidos ou mal formatados.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    ErrorMessage.class)))
        })
    public ResponseEntity<Void> updatePassword(@PathVariable(name = "id") final Long id, @RequestBody @Valid UsuarioSenhaDto senhaDto) {

        this.usuarioService
            .editarSenha(id, senhaDto.getSenhaAtual(), senhaDto.getNovaSenha(), senhaDto.getConfirmaSenha());

        return ResponseEntity
            .noContent()
            .build();
    }

    @GetMapping
    @Operation(summary = "Listar Usuários.", description = "Recurso para listar todos os Usuários.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Recursos listados com sucesso.",
                content = @Content(mediaType = "application/json", array =
                @ArraySchema(schema = @Schema(implementation = UsuarioResponseDto.class))))
        })
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {

        var users = this.usuarioService.buscarTodos();

        return ResponseEntity
            .ok()
            .body(UsuarioMapper.toListUsuarioResponseDto(users));
    }
}
