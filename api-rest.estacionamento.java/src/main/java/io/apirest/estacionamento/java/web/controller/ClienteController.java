package io.apirest.estacionamento.java.web.controller;

import io.apirest.estacionamento.java.security.jwt.JwtUserDetails;
import io.apirest.estacionamento.java.service.ClienteService;
import io.apirest.estacionamento.java.service.UsuarioService;
import io.apirest.estacionamento.java.web.dto.ClienteCreateDto;
import io.apirest.estacionamento.java.web.dto.ClienteResponseDto;
import io.apirest.estacionamento.java.web.exception.ErrorMessage;
import io.apirest.estacionamento.java.web.exception.ex.CpfUniqueViolationException;
import io.apirest.estacionamento.java.web.mapper.ClienteMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Clientes", description = "Contém todas as operações relativas ao recurso Cliente.")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    @Operation(summary = "Criar Cliente.", description = "Recurso para criar um cliente vinculado a um usuário cadastrado. A requisição exige uso de um bearer token. Acesso restrito a role CLIENTE.",
        security = @SecurityRequirement(name = "security"),
        responses = {
            @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso.",
                content = @Content(mediaType = "application/json;charset=UTF-8", schema =
                    @Schema(implementation = ClienteResponseDto.class))),
            @ApiResponse(responseCode = "403", description = "Recurso não permitido ao perfil de acesso.",
                content = @Content(mediaType = "application/json;charset=UTF-8", schema =
                    @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "409", description = "O CPF já possui cadastro no sistema.",
                content = @Content(mediaType = "application/json;charset=UTF-8", schema =
                    @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Recurso não processado por falta de dados ou dados inválidos.",
                content = @Content(mediaType = "application/json;charset=UTF-8", schema =
                    @Schema(implementation = ErrorMessage.class)))
        })
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto createDto,
                                                     @AuthenticationPrincipal JwtUserDetails jwtUserDetails) throws CpfUniqueViolationException {

        var cliente = ClienteMapper.toCliente(createDto);
        cliente.setUsuario(this.usuarioService.buscarPorId(jwtUserDetails.getId()));
        this.clienteService.salvar(cliente);

        return ResponseEntity
            .status(201)
            .body(ClienteMapper.toClienteResponseDto(cliente));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteResponseDto> getById(@PathVariable(name = "id") final Long id) {

        var cliente = this.clienteService.buscarPorId(id);

        return ResponseEntity
            .ok()
            .body(ClienteMapper.toClienteResponseDto(cliente));
    }
}

