package com.algaworks.junit.operacoesemcascata;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.chave_composta.ItemPedidoId;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class CascadeTypeMergeTest extends EntityManagerTest {

//    @Test
    void atualizarPedidoComItens() {

        var cliente = super.entityManager.find(Cliente.class, 3);
        var produto = super.entityManager.find(Produto.class, 1);

        var pedido = new Pedido();
        pedido.setId(1);
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedidoEnum.AGUARDANDO);

        var itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.getId().setPedidoId(pedido.getId());
        itemPedido.getId().setProdutoId(produto.getId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(3);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItensPedido(List.of(itemPedido));

        super.entityManager.getTransaction().begin();
        super.entityManager.merge(pedido); // cascade = {CascadeType.MERGE}
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var pedidoVerificacao = super.entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertTrue(pedidoVerificacao.getItensPedido().get(0).getQuantidade().equals(3));
    }
}

