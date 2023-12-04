package com.algaworks.junit.conhecendoentitymanager;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ContextoDePersistenciaTest extends EntityManagerTest {

    @Test
    void usarContextoPersistencia() {

        super.entityManager.getTransaction().begin();

        var produto = super.entityManager.find(Produto.class, 1);
        produto.setPreco(BigDecimal.TWO); // Update
        var produto2 = new Produto();
        produto2.setNome("Caneca de café");
        super.entityManager.merge(produto2); // Salva

        super.entityManager.flush();

        produto2.setPreco(BigDecimal.valueOf(15)); // Update

        super.entityManager.getTransaction().commit(); // Será feito um update, depois um save e depois um update
    }
}

