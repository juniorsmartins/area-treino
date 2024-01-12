package com.algaworks.junit.jpql;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class JoinJpqlTest extends EntityManagerTest {

    @Test
    void fazerInnerJoinComJPQL4() {
        String jpql = "select p from Pedido p inner join p.itensPedido i where i.precoProduto > 499";

        TypedQuery<Object[]> typedQuery = super.entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertEquals(2, lista.size());
    }

    @Test
    void fazerInnerJoinComJPQL3() {
        String jpql = "select p, pag from Pedido p inner join p.pagamento pag where pag.status = 'PROCESSANDO'";

        TypedQuery<Object[]> typedQuery = super.entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertEquals(2, lista.size());
    }

    @Test
    void fazerInnerJoinComJPQL2() {
        String jpql = "select p, pag from Pedido p inner join p.pagamento pag";

        TypedQuery<Object[]> typedQuery = super.entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertEquals(2, lista.size());
    }

    @Test
    void fazerInnerJoinComJPQL() {
        String jpql = "select p from Pedido p inner join p.pagamento pag";

        TypedQuery<Pedido> typedQuery = super.entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        Assertions.assertEquals(2, lista.size());
    }
}

