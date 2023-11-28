package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.enums.SexoClienteEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private SexoClienteEnum sexo;
}

