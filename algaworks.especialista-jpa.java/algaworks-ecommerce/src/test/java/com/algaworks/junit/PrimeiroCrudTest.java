package com.algaworks.junit;

import com.algaworks.ecommerce.model.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    void atualizarCliente() {
        var cliente = Cliente.builder()
            .id(5)
            .nome("Ron Atual Jeffries")
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(cliente);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var clientePraVerificar = this.entityManager.find(Cliente.class, 5);
        Assertions.assertNotNull(clientePraVerificar);
        Assertions.assertEquals(5, clientePraVerificar.getId());
        Assertions.assertEquals("Ron Atual Jeffries", clientePraVerificar.getNome());
    }

    @Test
    void removerCliente() {
        var cliente = this.entityManager.find(Cliente.class, 4);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(cliente);
        this.entityManager.getTransaction().commit();

        var clientePraVerificar = this.entityManager.find(Cliente.class, 4);
        Assertions.assertNull(clientePraVerificar);
    }

    @Test
    void buscarCliente() {
        var cliente = this.entityManager.find(Cliente.class, 3);

        Assertions.assertNotNull(cliente);
        Assertions.assertEquals(3, cliente.getId());
        Assertions.assertEquals("Eric Evans", cliente.getNome());
    }

    @Test
    void inserirClienteComMerge() {
        var cliente = Cliente.builder()
            .id(2)
            .nome("Robert Martin")
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(cliente);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var clientePraVerificar = this.entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clientePraVerificar);
        Assertions.assertEquals(cliente.getNome(), clientePraVerificar.getNome());
    }

    @Test
    void inserirClienteComPersist() {
        var cliente = Cliente.builder()
            .id(1)
            .nome("Martin Fowler")
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(cliente);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var clientePraVerificar = this.entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clientePraVerificar);
        Assertions.assertEquals(cliente.getNome(), clientePraVerificar.getNome());
    }
}

