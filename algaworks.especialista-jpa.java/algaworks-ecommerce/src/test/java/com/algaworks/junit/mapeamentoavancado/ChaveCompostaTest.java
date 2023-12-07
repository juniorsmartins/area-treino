package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.chave_composta.ItemPedidoId;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ChaveCompostaTest extends EntityManagerTest {

    @Test
    void salvarItem() {


        var cliente = super.entityManager.find(Cliente.class, 3);
        var produto = super.entityManager.find(Produto.class, 1);

        var pedido = Pedido.builder()
                .cliente(cliente)
                .dataCriacao(LocalDateTime.now())
                .status(StatusPedidoEnum.AGUARDANDO)
                .total(produto.getPreco())
                .build();

        var itemPedido = ItemPedido.builder()
                .id(new ItemPedidoId()) // Chave-composta com Embeddable/EmbeddedId
                .pedido(pedido)
//                .pedidoId(pedido.getId()) // Chave-composta com IdClass
                .produto(produto)
//                .produtoId(produto.getId()) // Chave-composta com IdClass
                .precoProduto(produto.getPreco())
                .quantidade(1)
                .build();

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(pedido);
        super.entityManager.persist(itemPedido);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var pedidoVerificacao = super.entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao);
        Assertions.assertFalse(pedidoVerificacao.getItensPedido().isEmpty());
    }

    @Test
    void buscarItem() {

        var itemPedido = super.entityManager.find(ItemPedido.class, new ItemPedidoId(1, 1));

        Assertions.assertNotNull(itemPedido);
    }
}

