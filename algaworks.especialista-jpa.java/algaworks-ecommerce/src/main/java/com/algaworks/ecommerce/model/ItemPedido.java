package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.chave_composta.ItemPedidoId;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@IdClass(ItemPedidoId.class)
@Table(name = "itens_pedido")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"pedidoId", "produtoId"})
public final class ItemPedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pedido_id") // O nome precisa ser o mesmo do relacionamento mais abaixo
    private Integer pedidoId;

    @Id
    @Column(name = "produto_id") // O nome precisa ser o mesmo do relacionamento mais abaixo
    private Integer produtoId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", insertable = false, updatable = false) // Mesmo nome do id - obrigatório o insertable e updatable false
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", insertable = false, updatable = false) // Mesmo nome do id - obrigatório o insertable e updatable false
    private Produto produto;

    @Column(name = "preco_produto")
    private BigDecimal precoProduto;

    private Integer quantidade;
}

