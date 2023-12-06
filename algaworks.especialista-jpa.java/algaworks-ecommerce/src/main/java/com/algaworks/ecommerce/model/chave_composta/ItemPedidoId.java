package com.algaworks.ecommerce.model.chave_composta;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"pedidoId", "produtoId"})
public class ItemPedidoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer pedidoId; // Igual está na classe ItemPedido

    private Integer produtoId; // Igual está na classe ItemPedido
}

