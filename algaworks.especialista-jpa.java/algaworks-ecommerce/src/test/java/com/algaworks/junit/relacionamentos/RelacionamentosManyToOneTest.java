package com.algaworks.junit.relacionamentos;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
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
            .dataPedido(LocalDateTime.now())
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

        var cliente = this.entityManager.find(Cliente.class, 4);
        var produto = this.entityManager.find(Produto.class, 4);

        var pedido = Pedido.builder()
            .cliente(cliente)
            .total(BigDecimal.ONE)
            .dataPedido(LocalDateTime.now())
            .dataConclusao(LocalDateTime.now().plusDays(1))
            .notaFiscalId(1)
            .status(StatusPedidoEnum.AGUARDANDO)
            .build();

        var itemPedido = ItemPedido.builder()
            .pedido(pedido)
            .produto(produto)
            .precoProduto(BigDecimal.TWO)
            .quantidade(4)
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(pedido);
        this.entityManager.persist(itemPedido);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var itemPedidoVerificar = this.entityManager.find(ItemPedido.class, itemPedido.getId());
        Assertions.assertNotNull(itemPedidoVerificar);
        Assertions.assertNotNull(itemPedidoVerificar.getPedido());
        Assertions.assertNotNull(itemPedidoVerificar.getProduto());
    }
}

