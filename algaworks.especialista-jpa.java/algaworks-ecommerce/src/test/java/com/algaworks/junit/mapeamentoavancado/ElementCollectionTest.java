package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ElementCollectionTest extends EntityManagerTest {

    @Test
    void aplicarTags() {

        super.entityManager.getTransaction().begin();

        var produto = super.entityManager.find(Produto.class, 1);
        produto.setTags(List.of("Ebook", "livro-digital"));

        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var produtoVerificado = super.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificado);
        Assertions.assertEquals(2, produtoVerificado.getTags().size());
    }
}

