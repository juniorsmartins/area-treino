package io.apirest.estacionamento.java.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSenhaDto {

    private String senhaAtual;

    private String novaSenha;

    private String confirmaSenha;
}

