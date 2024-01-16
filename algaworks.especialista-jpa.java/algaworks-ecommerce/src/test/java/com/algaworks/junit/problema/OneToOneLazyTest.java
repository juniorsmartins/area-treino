package com.algaworks.junit.problema;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class OneToOneLazyTest extends EntityManagerTest {

    @Test
    void mostrarProblema() {
        System.out.println("---------------------------------------");
        System.out.println("Buscar Pedido: \n");

        var pedido = super.entityManager.find(Pedido.class, 1);
        Assertions.assertNotNull(pedido);

        System.out.println("---------------------------------------");
        System.out.println("Buscar lista de Pedidos: \n");

        var lista = super.entityManager.createQuery("select p from Pedido p", Pedido.class)
            .getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }

    @Test
    public void melhorSolucaoParaProblemaEhJpqlComFetch() {
        System.out.println("BUSCANDO UM PEDIDO:");

        Pedido pedido = entityManager
                .createQuery("select p from Pedido p " +
                        "left join fetch p.pagamento " +
                        "left join fetch p.cliente " +
                        "left join fetch p.notaFiscal " +
                        "where p.id = 1", Pedido.class)
                .getSingleResult();
        Assertions.assertNotNull(pedido);

        System.out.println("BUSCANDO UMA LISTA DE PEDIDOS:");

        List<Pedido> lista = entityManager
                .createQuery("select p from Pedido p " +
                        "left join fetch p.pagamento " +
                        "left join fetch p.cliente " +
                        "left join fetch p.notaFiscal", Pedido.class)
                .getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }
}

