package com.desafiov2picpayjava.adapters.out.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "transferencias")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class TransferenciaOrm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pagador_id", nullable = false, updatable = false, referencedColumnName = "id")
    private CarteiraOrm pagador;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id", nullable = false, updatable = false, referencedColumnName = "id")
    private CarteiraOrm beneficiario;

    @Column(name = "valor", nullable = false, scale = 4, updatable = false)
    private BigDecimal value;

    @Column(name = "data_time_transacao", nullable = false, updatable = false)
    private OffsetDateTime dataTimeTransacao;
}

