package io.apirest.estacionamento.java.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDto {

    private Long id;

    private String username;

    private String role;
}

