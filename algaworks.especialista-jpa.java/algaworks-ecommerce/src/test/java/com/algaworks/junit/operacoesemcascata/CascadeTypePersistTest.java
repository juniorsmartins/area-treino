package com.algaworks.junit.operacoesemcascata;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.ItemPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.chave_composta.ItemPedidoId;
import com.algaworks.ecommerce.model.enums.SexoClienteEnum;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class CascadeTypePersistTest extends EntityManagerTest {

    @Test
    void persistirPedidoComItens() {

        Cliente cliente = super.entityManager.find(Cliente.class, 3);
        Produto produto = super.entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedidoEnum.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItensPedido(List.of(itemPedido));

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(pedido);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        Pedido pedidoVerificacao = super.entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao);
        Assertions.assertFalse(pedidoVerificacao.getItensPedido().isEmpty());
    }

    @Test
    void persistirItemPedidoComPedido() {

        Cliente cliente = super.entityManager.find(Cliente.class, 3);
        Produto produto = super.entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(produto.getPreco());
        pedido.setStatus(StatusPedidoEnum.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItensPedido(List.of(itemPedido));

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(itemPedido);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var itemPedidoVerificacao = super.entityManager.find(ItemPedido.class, itemPedido.getId());
        var pedidoVerificacao = super.entityManager.find(Pedido.class, itemPedido.getPedido().getId());
        Assertions.assertNotNull(itemPedidoVerificacao);
        Assertions.assertNotNull(itemPedidoVerificacao.getPedido());
        Assertions.assertNotNull(pedidoVerificacao);
        Assertions.assertFalse(pedidoVerificacao.getItensPedido().isEmpty());
        Assertions.assertEquals(itemPedidoVerificacao.getPedido().getId(),
                pedidoVerificacao.getItensPedido().get(0).getPedido().getId());
    }

    @Test
    void persistirPedidoComCliente() {

        Cliente cliente = new Cliente();
        cliente.setDataNascimento(LocalDate.of(1980, 1, 1));
        cliente.setSexo(SexoClienteEnum.MASCULINO);
        cliente.setNome("José Carlos");
        cliente.setCpf("01234567890");

        Pedido pedido = new Pedido();
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.ZERO);
        pedido.setStatus(StatusPedidoEnum.AGUARDANDO);

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(pedido);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var pedidoVerificacao = super.entityManager.find(Pedido.class, pedido.getId());
        var clienteVerificacao = super.entityManager.find(Cliente.class, pedido.getCliente().getId());
        Assertions.assertNotNull(pedidoVerificacao);
        Assertions.assertNotNull(clienteVerificacao);
        Assertions.assertEquals(pedidoVerificacao.getCliente().getId(),
                clienteVerificacao.getPedidos().get(0).getCliente().getId());
    }
}

