package com.algaworks.junit.relacionamentos;

import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.enums.StatusPagamentoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

class RelacionamentosOneToOneTest extends EntityManagerTest {

    @Test
    void verificarRelacionamentoPagamentoCartaoPedido() {

        var pedido = this.entityManager.find(Pedido.class, 1);
        Assertions.assertNull(pedido.getPagamento());

        var pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumeroCartao("1234");
        pagamentoCartao.setStatus(StatusPagamentoEnum.PROCESSANDO);
        pagamentoCartao.setPedido(pedido);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(pagamentoCartao);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var pagamentoCartaoVerificar = this.entityManager.find(PagamentoCartao.class, pagamentoCartao.getId());
        Assertions.assertNotNull(pagamentoCartaoVerificar);
        Assertions.assertNotNull(pagamentoCartaoVerificar.getPedido());
    }

    @Test
    void verificarRelacionamentoNotaFiscalPedido() {

        var pedido = this.entityManager.find(Pedido.class, 1);
        Assertions.assertNull(pedido.getNotaFiscal());

        var notaFiscal = NotaFiscal.builder()
            .pedido(pedido)
            .dataEmissao(Date.from(Instant.now()))
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(notaFiscal);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var notaFiscalVerificar = this.entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assertions.assertNotNull(notaFiscalVerificar);
        Assertions.assertNotNull(notaFiscalVerificar.getPedido());

        var pedidoVerificar = this.entityManager.find(Pedido.class, 1);
        Assertions.assertNotNull(pedidoVerificar);
        Assertions.assertNotNull(pedidoVerificar.getNotaFiscal());
    }
}

