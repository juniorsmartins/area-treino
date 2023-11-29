package com.algaworks.junit.relacionamentos;

import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AutoRelacionamentoTest extends EntityManagerTest {

    @Test
    void verificarAutoRelacionamento() {

        var categoriaPai = Categoria.builder()
            .nome("Eletrônicos")
            .build();

        var categoriaFilha = Categoria.builder()
            .nome("Celular")
            .categoriaPai(categoriaPai)
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(categoriaPai);
        this.entityManager.persist(categoriaFilha);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var categoriaFilhaVerificar = this.entityManager.find(Categoria.class, categoriaFilha.getId());
        Assertions.assertNotNull(categoriaFilhaVerificar);
        Assertions.assertNotNull(categoriaFilhaVerificar.getCategoriaPai());
        Assertions.assertEquals(categoriaFilhaVerificar.getCategoriaPai().getNome(), categoriaPai.getNome());

        var categoriaPaizao = this.entityManager.find(Categoria.class, categoriaPai.getId());
        Assertions.assertFalse(categoriaPaizao.getCategorias().isEmpty());
    }
}

