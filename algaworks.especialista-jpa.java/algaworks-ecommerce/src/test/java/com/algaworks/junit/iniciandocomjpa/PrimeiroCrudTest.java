package com.algaworks.junit.iniciandocomjpa;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.enums.SexoClienteEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    void atualizarCliente() {

        var cliente = new Cliente();
        cliente.setId(5);
        cliente.setNome("Ron Atual Jeffries");
        cliente.setCpf("99999999999");
        cliente.setSexo(SexoClienteEnum.MASCULINO);

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

        var cliente = new Cliente();
        cliente.setId(5);
        cliente.setNome("Robert Martin");

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
            .nome("Martin Fowler")
            .cpf("76789832145")
            .sexo(SexoClienteEnum.MASCULINO)
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

