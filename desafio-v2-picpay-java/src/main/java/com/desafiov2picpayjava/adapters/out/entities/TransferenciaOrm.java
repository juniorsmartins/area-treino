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
    @JoinColumn(name = "emitente_id")
    private CarteiraOrm emitente;

    @ManyToOne
    @JoinColumn(name = "recebedor_id")
    private CarteiraOrm recebedor;

    @Column(name = "valor", nullable = false, scale = 4)
    private BigDecimal valor;

    @Column(name = "data_time_transacao", nullable = false)
    private OffsetDateTime dataTimeTransacao;
}

