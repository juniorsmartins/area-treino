package com.algaworks.junit.conhecendoentitymanager;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Test;

class CachePrimeiroNivelTest extends EntityManagerTest {

    @Test
    void verificarCache() {

        var produto = super.entityManager.find(Produto.class, 1);

        System.out.println(produto.getNome());

        System.out.println("----------------------------------------------");

        var produtoResgatado = super.entityManager.find(Produto.class, produto.getId());
        System.out.println(produtoResgatado.getNome());
    }
}

