package com.algaworks.junit;

import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    void removerObjeto() {
        var produto = this.entityManager.find(Produto.class, 3);

        this.entityManager.getTransaction().begin();
        this.entityManager.remove(produto);
        this.entityManager.getTransaction().commit();

        var produtoPraVerificar = this.entityManager.find(Produto.class, 3);
        Assertions.assertNull(produtoPraVerificar);
    }

    @Test
    void inserirPrimeiroObjeto() {
        var produto = Produto.builder()
            .id(2)
            .nome("Câmera Canon")
            .descricao("A melhor definição para suas fotos.")
            .preco(BigDecimal.valueOf(5000))
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(produto);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear(); // Limpa a memória do EntityManager. Obrigará o find() abaixo a ir no banco buscar, ao invés de pegar na metória do EntityManager.

        var produtoPraVerificar = this.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoPraVerificar);
    }
}

