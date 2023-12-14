package io.algaworksalgafoodjava.domain.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "restaurantes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class Restaurante implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 50, unique = true, nullable = false)
    private String nome;

    @Column(name = "taxa_frete", precision = 10, scale = 2, nullable = false)
    private BigDecimal taxaFrete;

    // Mandante Owner
    @ManyToOne(optional = false)
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(name = "restaurantes_formas_pagamento",
        joinColumns = @JoinColumn(name = "restaurante_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_restaurantes_formas_pagamento_restaurante")),
        inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id", referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_restaurantes_formas_pagamento_formas_pagamento"))
    )
    private List<FormaPagamento> formasPagamento;
}

