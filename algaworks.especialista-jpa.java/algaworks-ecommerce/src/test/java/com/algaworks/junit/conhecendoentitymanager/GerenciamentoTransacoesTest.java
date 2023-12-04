package com.algaworks.junit.conhecendoentitymanager;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Test;

class GerenciamentoTransacoesTest extends EntityManagerTest {

    @Test
    void abrirFecharCancelarTransacao() {
        var pedido = super.entityManager.find(Pedido.class, 1);

        super.entityManager.getTransaction().begin();
        pedido.setStatus(StatusPedidoEnum.PAGO);

        if (pedido.getPagamentoCartao() != null) {
            super.entityManager.getTransaction().commit();
        } else {
            super.entityManager.getTransaction().rollback();
        }
    }

//    @Test
//    void abrirFecharCancelarTransacao2() {
//
//        try {
//            super.entityManager.getTransaction().begin();
//            this.metodoDoNegocio();
//            super.entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            super.entityManager.getTransaction().rollback();
//            throw e;
//        }
//    }

    private void metodoDoNegocio() {
        var pedido = super.entityManager.find(Pedido.class, 1);
        pedido.setStatus(StatusPedidoEnum.PAGO);

        if (pedido.getPagamentoCartao() == null) {
            throw new RuntimeException("Pedido ainda não pago.");
        }
    }
}

