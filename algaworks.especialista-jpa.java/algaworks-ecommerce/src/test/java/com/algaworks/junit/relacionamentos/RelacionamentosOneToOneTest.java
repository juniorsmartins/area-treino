package com.algaworks.junit.relacionamentos;

import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.enums.StatusPagamentoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RelacionamentosOneToOneTest extends EntityManagerTest {

    @Test
    void verificarRelacionamentoPagamentoCartaoPedido() {

        var pedido = this.entityManager.find(Pedido.class, 1);
        Assertions.assertNull(pedido.getPagamentoCartao());

        var pagamentoCartao = PagamentoCartao.builder()
            .numero("1234")
            .status(StatusPagamentoEnum.PROCESSANDO)
            .pedido(pedido)
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(pagamentoCartao);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var pagamentoCartaoVerificar = this.entityManager.find(PagamentoCartao.class, pagamentoCartao.getId());
        Assertions.assertNotNull(pagamentoCartaoVerificar);
        Assertions.assertNotNull(pagamentoCartaoVerificar.getPedido());
    }
}

