package io.apirest.estacionamento.java.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioCreateDto {

    @Email(regexp = "^[a-z0-9_+.]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6, max = 6)
    private String password;
}

