package com.algaworks.junit.jpql;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class JoinWithFetchTest extends EntityManagerTest {

    @Test
    void fazerJoinComFetch() {
        String jpql = "select p from Pedido p left outer join fetch p.pagamento " +
                "inner join fetch p.cliente " +
                "left outer join fetch p.notaFiscal ";

        TypedQuery<Pedido> typedQuery = super.entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }
}

