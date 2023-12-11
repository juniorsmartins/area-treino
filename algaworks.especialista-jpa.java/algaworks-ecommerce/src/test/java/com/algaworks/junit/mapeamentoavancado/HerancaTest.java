package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pagamento;
import com.algaworks.ecommerce.model.PagamentoCartao;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.enums.SexoClienteEnum;
import com.algaworks.ecommerce.model.enums.StatusPagamentoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class HerancaTest extends EntityManagerTest {

    @Test
    void salvarClienteComHeranca() {

        var cliente = Cliente.builder()
                .nome("Andy Hunt")
                .cpf("65665665")
                .sexo(SexoClienteEnum.MASCULINO)
                .dataNascimento(LocalDate.now().minusYears(30))
                .build();

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(cliente);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var clienteVerificado = super.entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificado.getId());
    }

    @Test
    void buscarPagamentos() {
        List<Pagamento> pagamentos = super.entityManager
                .createQuery("select p from Pagamento p")
                .getResultList();

        Assertions.assertFalse(pagamentos.isEmpty());
    }

    @Test
    void incluirPagamentoPedido() {

        var pedido = super.entityManager.find(Pedido.class, 3);

        var pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setPedido(pedido);
        pagamentoCartao.setStatus(StatusPagamentoEnum.PROCESSANDO);
        pagamentoCartao.setNumeroCartao("123");

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(pagamentoCartao);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var pedidoVerificado = super.entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificado.getPagamento());
    }
}

