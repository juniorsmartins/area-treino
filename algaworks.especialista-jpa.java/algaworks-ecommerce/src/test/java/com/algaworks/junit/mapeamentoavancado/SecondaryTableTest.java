package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.enums.SexoClienteEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SecondaryTableTest extends EntityManagerTest {

    @Test
    void salvarCliente() {

        var cliente = Cliente.builder()
                .nome("Ward Cunningham")
                .sexo(SexoClienteEnum.MASCULINO)
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .cpf("88877712343")
                .build();

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(cliente);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var clienteVerificado = super.entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertNotNull(clienteVerificado.getSexo());
        Assertions.assertNotNull(clienteVerificado.getDataNascimento());
    }
}

