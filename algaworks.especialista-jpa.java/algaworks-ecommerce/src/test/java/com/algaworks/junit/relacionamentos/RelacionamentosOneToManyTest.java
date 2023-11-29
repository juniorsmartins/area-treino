package com.algaworks.junit.relacionamentos;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
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
            .dataPedido(LocalDateTime.now())
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
        Assertions.assertEquals(clienteVerificar.getPedidos().size(), 1);
    }
}

