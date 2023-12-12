package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Length;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "produtos",
       uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = {"nome"}) },
       indexes = { @Index(name = "idx_nome", columnList = "nome")}
)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners({ GenericoListener.class })
public final class Produto extends EntidadeBaseInteger implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Lob
    @Column(name = "descricao")
    private String descricao;

    @Column(precision = 19, scale = 2)
    private BigDecimal preco;

    @ManyToMany
    @JoinTable(name = "produto_categoria",
        joinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    )
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @ElementCollection
    @CollectionTable(name = "produto_tag", // Nomea a tabela
        joinColumns = @JoinColumn(name = "produto_id")) // Define que o ID de produto estará nessa tabela
    @Column(name = "tag", length = 50, nullable = false) // Define o nome da coluna na tabela que receberá o item da lista
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo",
        joinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Atributo> atributos;

    @Lob
    @Column(length = Length.LOB_DEFAULT)
    private byte[] foto;
}

