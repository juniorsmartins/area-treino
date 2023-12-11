package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.chave_composta.ItemPedidoId;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class ItemPedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId // Meu ID (chave-composta) incorporada
    private ItemPedidoId id;

    @MapsId("pedidoId") // Nome que está no ItemPedidoId
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @MapsId("produtoId") // Nome que está no ItemPedidoId
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "preco_produto", precision = 19, scale = 2, nullable = false)
    private BigDecimal precoProduto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
}

