package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notas_fiscal")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class NotaFiscal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "pedido_id")
//    @JoinTable(name = "pedido_nota_fiscal_id",
//        joinColumns = @JoinColumn(name = "nota_fiscal_id", unique = true),
//        inverseJoinColumns = @JoinColumn(name = "pedido_id", unique = true)
//    )
    private Pedido pedido;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;
}

