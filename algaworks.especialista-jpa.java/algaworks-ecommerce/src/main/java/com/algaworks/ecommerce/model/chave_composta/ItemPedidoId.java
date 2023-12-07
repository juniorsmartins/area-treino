package com.algaworks.ecommerce.model.chave_composta;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Embeddable // Incorporar em outra classe
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"pedidoId", "produtoId"})
public class ItemPedidoId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "pedido_id")
    private Integer pedidoId;

    @Column(name = "produto_id")
    private Integer produtoId;
}

