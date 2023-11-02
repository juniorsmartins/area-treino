package io.apirest.estacionamento.java.security.controller;

import io.apirest.estacionamento.java.security.jwt.JwtToken;
import io.apirest.estacionamento.java.security.jwt.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1")
public class AutenticacaoController {

    private final JwtUserDetailsService jwtUserDetailsService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<JwtToken> autenticar() {

    }
}

