package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PropriedadesTransientesTest extends EntityManagerTest {

    @Test
    void validarPrimeiroNome() {

        var cliente = super.entityManager.find(Cliente.class, 3);

        Assertions.assertEquals("Eric", cliente.getPrimeiroNome());
    }
}

