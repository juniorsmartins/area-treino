package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.model.converter.BooleanToSimNaoConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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

    @Version
    private Long version; // Guarda versão da entidade para concorrência LockOtimista (OptimisticLockException) - Pode ser do tipo Integer, Long, Date e LocalDateTime

    @NotNull
    @PastOrPresent
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PastOrPresent
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @NotBlank
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Lob
    @Column(name = "descricao")
    private String descricao;

    @Positive
    @Column(precision = 19, scale = 2)
    private BigDecimal preco;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(name = "produto_categoria",
        joinColumns = @JoinColumn(name = "produto_id", nullable = false, referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_produto_categoria_produto")),
        inverseJoinColumns = @JoinColumn(name = "categoria_id", nullable = false, referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria"))
    )
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    @ElementCollection
    @CollectionTable(name = "produto_tag", // Nomeia a tabela
        joinColumns = @JoinColumn(name = "produto_id", foreignKey = @ForeignKey(name = "fk_produto_produto_tag"))) // Define que o ID de produto estará nessa tabela
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

    @Convert(converter = BooleanToSimNaoConverter.class)
    @NotNull
    @Column(name = "ativo", nullable = false, length = 3)
    private Boolean ativo = Boolean.FALSE;
}

