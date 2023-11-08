package io.apirest.estacionamento.java.security.controller;

import io.apirest.estacionamento.java.security.dto.UsuarioLoginDto;
import io.apirest.estacionamento.java.security.jwt.JwtUserDetailsService;
import io.apirest.estacionamento.java.web.dto.UsuarioResponseDto;
import io.apirest.estacionamento.java.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação", description = "Recurso para proceder com autenticação na API.")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1")
public class AutenticacaoController {

    private final JwtUserDetailsService jwtUserDetailsService;

    private final AuthenticationManager authenticationManager;

    @PostMapping(path = "/auth")
    @Operation(summary = "Autenticar na API.", description = "Recurso para Usuário efetuar login.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Autenticação realizada com sucesso e retorno de bearer token.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    UsuarioResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Credenciais inválidas.",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    ErrorMessage.class))),
            @ApiResponse(responseCode = "422", description = "Campo(s) inválido(s).",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                    ErrorMessage.class)))
            })
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto loginDto, HttpServletRequest request) {

        log.info("Processo de autenticação pelo login '{}'", loginDto.getUsername());

        try {
            var authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
            this.authenticationManager.authenticate(authenticationToken);
            var token = this.jwtUserDetailsService.getTokenAuthenticated(loginDto.getUsername());
            return ResponseEntity
                .ok(token);

        } catch (AuthenticationException ex) {
            log.warn("Bad Credentials from username '{}'", loginDto.getUsername());
        }
        return ResponseEntity
            .badRequest()
            .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais inválidas"));
    }
}

