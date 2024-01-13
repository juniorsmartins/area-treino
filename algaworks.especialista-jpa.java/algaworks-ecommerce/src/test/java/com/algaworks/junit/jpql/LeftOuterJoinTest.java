package com.algaworks.junit.jpql;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LeftOuterJoinTest extends EntityManagerTest {

    @Test
    void fazerLeftOuterJoinComOnParaFiltrarPagamento() { // Pega todos os Pedidos, mais os pagamentos relacionados e filtrados por status
        String jpql = "select p from Pedido p left outer join p.pagamento pag on pag.status = 'PROCESSANDO'";

        TypedQuery<Pedido> typedQuery = super.entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    void fazerLeftOuterJoinComJPQL() {
        String jpql = "select p from Pedido p left outer join p.pagamento pag";

        TypedQuery<Pedido> typedQuery = super.entityManager.createQuery(jpql, Pedido.class);
        List<Pedido> lista = typedQuery.getResultList();

        Assertions.assertFalse(lista.isEmpty());
    }
}

