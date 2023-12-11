package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public final class Atributo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "valor")
    private String valor;
}

