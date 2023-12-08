package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.enums.SexoClienteEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HerancaTest extends EntityManagerTest {

    @Test
    void salvarClienteComHeranca() {

        var cliente = Cliente.builder()
                .nome("Andy Hunt")
                .sexo(SexoClienteEnum.MASCULINO)
                .build();

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(cliente);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var clienteVerificado = super.entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificado.getId());
    }
}

