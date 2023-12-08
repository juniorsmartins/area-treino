package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Atributo;
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

    @Test
    void aplicarAtributos() {

        super.entityManager.getTransaction().begin();

        var produto = super.entityManager.find(Produto.class, 3);

        Assertions.assertTrue(produto.getAtributos().isEmpty());

        var atributo1 = Atributo.builder()
                .nome("Cor")
                .valor("Branca")
                .build();

        var atributo2 = Atributo.builder()
                .nome("Quantia de Páginas")
                .valor("650")
                .build();

        produto.setAtributos(List.of(atributo1, atributo2));

        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var produtoVerificado = super.entityManager.find(Produto.class, produto.getId());
        Assertions.assertFalse(produtoVerificado.getAtributos().isEmpty());
    }
}

