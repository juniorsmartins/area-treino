package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public final class Atributo {

    @Column(name = "nome")
    private String nome;

    @Column(name = "valor")
    private String valor;
}

