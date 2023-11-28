package com.algaworks.junit.mapeamentobasico;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.enums.SexoClienteEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MapeandoEnumeracoesTest extends EntityManagerTest {

    @Test
    void testarEnum() {

        var cliente = Cliente.builder()
            .nome("José Mineiro")
            .sexo(SexoClienteEnum.MASCULINO)
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(cliente);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var clienteVerificacao = this.entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificacao);
    }
}

