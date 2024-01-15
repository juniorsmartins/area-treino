package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public final class EnderecoEntregaPedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}")
    @Column(name = "cep", length = 9)
    private String cep;

    @NotBlank
    @Column(name = "logradouro", length = 100)
    private String logradouro;

    @NotBlank
    @Column(name = "numero", length = 10)
    private String numero;

    @NotBlank
    @Column(name = "bairro", length = 50)
    private String bairro;

    @NotBlank
    @Column(name = "cidade", length = 50)
    private String cidade;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(name = "estado", length = 2)
    private String estado;

    @Column(name = "complemento", length = 50)
    private String complemento;
}
