package com.algaworks.junit.relacionamentos;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.chave_composta.ItemPedidoId;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class RelacionamentosManyToOneTest extends EntityManagerTest {

    @Test
    void verificarRelacionamentoPedido() {

        var cliente = this.entityManager.find(Cliente.class, 3);

        var pedido = Pedido.builder()
            .status(StatusPedidoEnum.AGUARDANDO)
            .dataCriacao(LocalDateTime.now())
            .cliente(cliente)
            .total(BigDecimal.TEN)
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(pedido);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var pedidoVerificar = this.entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificar);
        Assertions.assertNotNull(pedidoVerificar.getCliente());
    }

    @Test
    void verificarRelacionamentoItemPedido() {

        this.entityManager.getTransaction().begin();

        var cliente = this.entityManager.find(Cliente.class, 4);
        var produto = this.entityManager.find(Produto.class, 4);

        var pedido = Pedido.builder()
            .cliente(cliente)
            .total(BigDecimal.ONE)
            .dataCriacao(LocalDateTime.now())
            .dataConclusao(LocalDateTime.now().plusDays(1))
            .status(StatusPedidoEnum.AGUARDANDO)
            .build();

        this.entityManager.persist(pedido);
        super.entityManager.flush();

        var itemPedido = ItemPedido.builder()
            .pedido(pedido)
            .pedidoId(pedido.getId())
            .produto(produto)
            .produtoId(produto.getId())
            .precoProduto(BigDecimal.TWO)
            .quantidade(4)
            .build();

        this.entityManager.persist(itemPedido);

        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var itemPedidoVerificar = this.entityManager.find(ItemPedido.class,
                new ItemPedidoId(pedido.getId(), produto.getId()));

        Assertions.assertNotNull(itemPedidoVerificar);
        Assertions.assertNotNull(itemPedidoVerificar.getPedido());
        Assertions.assertNotNull(itemPedidoVerificar.getProduto());
    }
}

