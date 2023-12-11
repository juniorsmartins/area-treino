package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categorias",
       uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = "nome") },
       indexes = { @Index(name = "idx_nome", columnList = "nome") }
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class Categoria extends EntidadeBaseInteger implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @ManyToOne // Lado Owner
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;

    @OneToMany(mappedBy = "categoriaPai") // Lado Non-Owning
    private List<Categoria> categorias;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos;
}

