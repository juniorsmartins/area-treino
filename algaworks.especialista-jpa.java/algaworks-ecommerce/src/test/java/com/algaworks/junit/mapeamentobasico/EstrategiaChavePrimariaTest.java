package com.algaworks.junit.mapeamentobasico;

import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EstrategiaChavePrimariaTest extends EntityManagerTest {

    @Test
    void testarEstrategiaChave() {

        var categoria = Categoria.builder()
            .nome("Eletrônicos")
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(categoria);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var categoriaVerificar = this.entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertNotNull(categoriaVerificar);
    }
}

