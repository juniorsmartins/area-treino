package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataPedido;

    private LocalDateTime dataConclusao;

    private Integer notaFiscalId;

    private StatusPedidoEnum status;

    private BigDecimal total;
}

