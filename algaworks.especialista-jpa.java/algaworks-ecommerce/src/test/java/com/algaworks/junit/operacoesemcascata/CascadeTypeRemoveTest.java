package com.algaworks.junit.operacoesemcascata;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;

class CascadeTypeRemoveTest extends EntityManagerTest {

//    @Test
    void removerPedidoAndUmItem() {

        var pedido = super.entityManager.find(Pedido.class, 3);
        Assertions.assertNotNull(pedido);
        Assertions.assertEquals(1, pedido.getItensPedido().size());

        super.entityManager.getTransaction().begin();
        super.entityManager.remove(pedido); // cascade = {CascadeType.REMOVE}
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var pedidoVerificacao = super.entityManager.find(Pedido.class, 3);
        Assertions.assertNull(pedidoVerificacao);
    }
}

