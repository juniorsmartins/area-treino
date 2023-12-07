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

class RelacionamentosOneToManyTest extends EntityManagerTest {

    @Test
    void verificarRelacionamentoCliente() {

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

        var clienteVerificar = this.entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificar);
        Assertions.assertNotNull(clienteVerificar.getPedidos());
        Assertions.assertFalse(clienteVerificar.getPedidos().isEmpty());
    }

    @Test
    void verificarRelacionamentoPedido() {

        var cliente = super.entityManager.find(Cliente.class, 3);
        var produto = super.entityManager.find(Produto.class, 1);

        var pedido = Pedido.builder()
                .status(StatusPedidoEnum.AGUARDANDO)
                .dataCriacao(LocalDateTime.now())
                .cliente(cliente)
                .total(BigDecimal.TEN)
                .build();

        var itemPedido = ItemPedido.builder()
                .id(new ItemPedidoId())
                .pedido(pedido)
                .produto(produto)
                .precoProduto(produto.getPreco())
                .quantidade(1)
                .build();

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(pedido);
        super.entityManager.persist(itemPedido);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var pedidoVerificado = super.entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertFalse(pedidoVerificado.getItensPedido().isEmpty());
    }
}

