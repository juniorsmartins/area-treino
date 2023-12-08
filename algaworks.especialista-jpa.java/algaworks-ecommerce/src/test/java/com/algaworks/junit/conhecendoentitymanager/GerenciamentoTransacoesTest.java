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

        if (pedido.getPagamento() != null) {
            super.entityManager.getTransaction().commit();
        } else {
            super.entityManager.getTransaction().rollback();
        }
    }

    @Test
    void testarRollback() { // Nada é salvo no banco

        try {
            super.entityManager.getTransaction().begin();
            this.metodoDoNegocio();
            super.entityManager.getTransaction().commit();
        } catch (Exception e) {
            super.entityManager.getTransaction().rollback();
            System.out.println("\n----- Exceção Lançada ----- : " + e.getMessage());
        }
    }

    private void metodoDoNegocio() {
        var pedido = super.entityManager.find(Pedido.class, 1);
        pedido.setStatus(StatusPedidoEnum.PAGO);

        if (pedido.getPagamento() == null) {
            throw new RuntimeException("Pedido ainda não pago.");
        }
    }

    @Test
    void testarFlush() { // Salvará no banco, mas dará Rollback e retirará do banco

        try {
            super.entityManager.getTransaction().begin();

            var pedido = super.entityManager.find(Pedido.class, 1);
            pedido.setStatus(StatusPedidoEnum.PAGO);

            super.entityManager.flush();

            if (pedido.getPagamento() == null) {
                throw new RuntimeException("Pedido ainda não pago.");
            }

            super.entityManager.getTransaction().commit();
        } catch (Exception e) {
            super.entityManager.getTransaction().rollback();
            System.out.println("\n----- Exceção Lançada ----- : " + e.getMessage());
        }
    }
}

