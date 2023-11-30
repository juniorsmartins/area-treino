package com.algaworks.junit.relacionamentos;

import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RelacionamentosManyToManyTest extends EntityManagerTest {

    @Test
    void verificarRelacionamentoProdutoCategoria() {

        var produto = this.entityManager.find(Produto.class, 1);
        Assertions.assertTrue(produto.getCategorias().isEmpty());

        var categoria = this.entityManager.find(Categoria.class, 1);
        Assertions.assertTrue(produto.getCategorias().isEmpty());

        produto.getCategorias().add(categoria);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(produto);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var produtoVerificar = this.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificar);
        Assertions.assertFalse(produtoVerificar.getCategorias().isEmpty());

        var categoriaVerificar = this.entityManager.find(Categoria.class, categoria.getId());
        Assertions.assertNotNull(categoriaVerificar);
        Assertions.assertFalse(categoriaVerificar.getProdutos().isEmpty());
    }
}

