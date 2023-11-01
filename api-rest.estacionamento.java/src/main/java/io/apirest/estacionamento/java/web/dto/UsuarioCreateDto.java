package io.apirest.estacionamento.java.web.dto;

import io.apirest.estacionamento.java.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDto {

    private String username;

    private String password;
}

