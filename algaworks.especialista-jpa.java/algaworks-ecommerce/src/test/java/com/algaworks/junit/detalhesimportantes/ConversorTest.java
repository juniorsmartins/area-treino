package com.algaworks.junit.detalhesimportantes;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class ConversorTest extends EntityManagerTest {

    @Test
    void converter() {

        var produto = new Produto();
        produto.setDataCriacao(LocalDateTime.now());
        produto.setNome("Carregador de Notebook Dell");
        produto.setAtivo(Boolean.TRUE);

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(produto);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var produtoVerificacao = super.entityManager.find(Produto.class, produto.getId());
        Assertions.assertTrue(produtoVerificacao.getAtivo());
    }
}

