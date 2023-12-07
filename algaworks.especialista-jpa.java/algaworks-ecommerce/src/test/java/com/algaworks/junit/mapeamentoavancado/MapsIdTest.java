package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.*;
import com.algaworks.ecommerce.model.chave_composta.ItemPedidoId;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

class MapsIdTest extends EntityManagerTest {

    @Test
    void inserirNotaFiscal() {

        var pedido = super.entityManager.find(Pedido.class, 1);

        var notaFiscal = NotaFiscal.builder()
                .pedido(pedido)
                .dataEmissao(new Date())
                .xml("<xml/>")
                .build();

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(notaFiscal);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var notaFiscalVerificada = super.entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assertions.assertNotNull(notaFiscalVerificada);
        Assertions.assertEquals(pedido.getId(), notaFiscalVerificada.getId());
    }

    @Test
    void inserirItemPedido() {

        var cliente = super.entityManager.find(Cliente.class, 3);
        var produto = super.entityManager.find(Produto.class, 1);

        var pedido = Pedido.builder()
                .cliente(cliente)
                .dataCriacao(LocalDateTime.now())
                .status(StatusPedidoEnum.AGUARDANDO)
                .total(produto.getPreco())
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

        var itemPedidoVerificado = super.entityManager.find(ItemPedido.class,
                new ItemPedidoId(pedido.getId(), produto.getId()));
        Assertions.assertNotNull(itemPedidoVerificado);
    }
}
