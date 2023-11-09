package com.desafiov2picpayjava.adapters.out.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "carteiras")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class CarteiraOrm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
    private long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "saldo", nullable = false, scale = 4)
    private BigDecimal saldo;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioOrm usuario;
}
