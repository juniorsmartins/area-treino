package com.algaworks.junit.conhecendoentitymanager;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class ListenerTest extends EntityManagerTest {

    @Test
    void carregarEntidadesListener() {

        var produto = super.entityManager.find(Produto.class, 1);
        var pedido = super.entityManager.find(Pedido.class, 1);
    }

    @Test
    void acionarListener() {

        var cliente = super.entityManager.find(Cliente.class, 3);

        var pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.TEN);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedidoEnum.AGUARDANDO);

        super.entityManager.getTransaction().begin();

        super.entityManager.persist(pedido);
        super.entityManager.flush(); // Obrigará a persistência de pedido, consequentemente acionará o callback @PrePersist

        pedido.setStatus(StatusPedidoEnum.PAGO); // Quando a transação terminar e der o commit, isso aqui acionará @PreUpdate

        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var pedidoVerificacao = super.entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assertions.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
    }
}

