package io.apirest.estacionamento.java.web.controller;

import io.apirest.estacionamento.java.security.jwt.JwtUserDetails;
import io.apirest.estacionamento.java.service.ClienteService;
import io.apirest.estacionamento.java.service.UsuarioService;
import io.apirest.estacionamento.java.web.dto.ClienteCreateDto;
import io.apirest.estacionamento.java.web.dto.ClienteResponseDto;
import io.apirest.estacionamento.java.web.exception.ex.CpfUniqueViolationException;
import io.apirest.estacionamento.java.web.mapper.ClienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    private final UsuarioService usuarioService;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<ClienteResponseDto> create(@RequestBody @Valid ClienteCreateDto createDto,
                                                     @AuthenticationPrincipal JwtUserDetails jwtUserDetails) throws CpfUniqueViolationException {

        var cliente = ClienteMapper.toCliente(createDto);
        cliente.setUsuario(this.usuarioService.buscarPorId(jwtUserDetails.getId()));
        this.clienteService.salvar(cliente);

        return ResponseEntity
            .status(201)
            .body(ClienteMapper.toClienteResponseDto(cliente));
    }
}

