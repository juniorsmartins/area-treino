package com.algaworks.junit.jpql;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicoJPQLTest extends EntityManagerTest {

    @Test
    void buscarPorIdentificador() {
        // entityManager.find(Pedido.class, 1);
        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = typedQuery.getSingleResult();

        Assertions.assertNotNull(pedido);
    }

    @Test
    void mostrarDiferencaQueries() {
        String jpql = "select p from Pedido p where p.id = 1";

        TypedQuery<Pedido> typedQuery = super.entityManager.createQuery(jpql, Pedido.class); // Jpa versão mais nova
        Pedido pedido1 = typedQuery.getSingleResult();
        Assertions.assertNotNull(pedido1);

        Query query = super.entityManager.createQuery(jpql); // Jpa varsão mais antiga
        Pedido pedido2 = (Pedido) query.getSingleResult(); // A diferença entre as duas é basicamente a necessidade de conversão
        Assertions.assertNotNull(pedido2);
    }
}

