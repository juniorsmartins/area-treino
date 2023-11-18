package io.algaworksalgafoodjava.domain.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "restaurantes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Restaurante implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 50, unique = true, nullable = false)
    private String nome;

    @Column(name = "taxa_frete", precision = 10, scale = 2, nullable = false)
    private BigDecimal taxaFrete = BigDecimal.ZERO;

    @ManyToOne
    private Cozinha cozinha;
}

