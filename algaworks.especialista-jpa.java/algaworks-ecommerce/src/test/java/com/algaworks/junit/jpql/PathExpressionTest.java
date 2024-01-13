package com.algaworks.junit.jpql;

import com.algaworks.junit.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PathExpressionTest extends EntityManagerTest {

    @Test
    void usarPathExpressionNoWhere() { // Path Expression = p.cliente.nome
        String jpql = "select p from Pedido p where p.cliente.nome = 'Ward Cunningham - Ágil'";

        TypedQuery<String> typedQuery = super.entityManager.createQuery(jpql, String.class);
        List<String> lista = typedQuery.getResultList();

        Assertions.assertEquals(2, lista.size());
    }

    @Test
    void usarPathExpressionParaSelecionarNomes() {
        String jpql = "select p.cliente.nome from Pedido p";

        TypedQuery<String> typedQuery = super.entityManager.createQuery(jpql, String.class);
        List<String> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }
}

