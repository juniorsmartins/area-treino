package com.algaworks.junit.relacionamentos;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemovendoEntidadesReferenciadasTest extends EntityManagerTest {

    @Test
    void verificarRemocaoDeEntidadesReferenciadas() {

        var pedido = this.entityManager.find(Pedido.class, 1);
        var itensPedido = pedido.getItensPedido();

        this.entityManager.getTransaction().begin();
        itensPedido.forEach(super.entityManager::remove);
        this.entityManager.remove(pedido);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var pedidoVerificar = this.entityManager.find(Pedido.class, 1);
        Assertions.assertNull(pedidoVerificar);
    }
}

