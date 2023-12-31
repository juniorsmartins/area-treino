package com.algaworks.junit.iniciandocomjpa;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OperacoesComTransacaoTest extends EntityManagerTest {

    @Test
    void impedirOperacaoComBancoDeDados() {
        var produto = this.entityManager.find(Produto.class, 1);

        this.entityManager.detach(produto); // Detach desanexou o produto do banco de dados e impediu o restante da operação.

        this.entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite Geração 2");
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var produtoPraVerificar = this.entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals("Kindle", produtoPraVerificar.getNome());
    }

    @Test
    void mostrarDiferencaPersistAndMerge() {
        var produtoPersist = Produto.builder()
            .nome("Smartphone One Plus")
            .descricao("O processador mais rápido.")
            .preco(BigDecimal.valueOf(2450))
            .dataCriacao(LocalDateTime.now())
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone Two Plus"); // Essa alteração é efetivada mesmo após a operação de persistir.
        this.entityManager.getTransaction().commit();

        this.entityManager.clear(); // Limpa a memória do EntityManager. Obrigará o find() abaixo a ir no banco buscar, ao invés de pegar na metória do EntityManager.

        var produtoPraVerificar = this.entityManager.find(Produto.class, produtoPersist.getId());
        Assertions.assertNotNull(produtoPraVerificar);

        var produtoMerge = new Produto();
        produtoMerge.setId(6);
        produtoMerge.setNome("Notebook Dell XPS-9320");
        produtoMerge.setDescricao("O processador mais rápido.");
        produtoMerge.setPreco(BigDecimal.valueOf(11450));
        produtoMerge.setDataCriacao(LocalDateTime.now());

        this.entityManager.getTransaction().begin();
        produtoMerge = this.entityManager.merge(produtoMerge); // É preciso capturar o retorno do Merge se quiser fazer alteração posterior.
        produtoMerge.setDescricao("32 de Ram e o processador mais rápido.");
        this.entityManager.getTransaction().commit();

        this.entityManager.clear(); // Limpa a memória do EntityManager. Obrigará o find() abaixo a ir no banco buscar, ao invés de pegar na metória do EntityManager.

        var produtoPraVerificar2 = this.entityManager.find(Produto.class, produtoMerge.getId());
        Assertions.assertNotNull(produtoPraVerificar2);
    }

    @Test
    void inserirObjetoComMerge() {

        var produto = new Produto();
        produto.setId(4);
        produto.setNome("Microfone Sony");
        produto.setDescricao("A melhor qualidade de som.");
        produto.setDataCriacao(LocalDateTime.now());
        produto.setPreco(BigDecimal.valueOf(1050));

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(produto);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear(); // Limpa a memória do EntityManager. Obrigará o find() abaixo a ir no banco buscar, ao invés de pegar na metória do EntityManager.

        var produtoPraVerificar = this.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoPraVerificar);
        Assertions.assertEquals(produto.getNome(), produtoPraVerificar.getNome());
    }

    @Test
    void atualizarObjetoGerenciado() {
        var produto = this.entityManager.find(Produto.class, 1);

        this.entityManager.getTransaction().begin();
        produto.setNome("Kindle Paperwhite Geração 2");
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var produtoPraVerificar = this.entityManager.find(Produto.class, produto.getId());
        Assertions.assertEquals(produto.getNome(), produtoPraVerificar.getNome());
    }

    @Test
    void atualizarObjeto() {

        var produto = new Produto();
        produto.setId(1);
        produto.setNome("Kindle Paperwhite");
        produto.setDescricao("Novo Kindle");
        produto.setPreco(BigDecimal.valueOf(591.67));

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(produto); // Se algum campo do produto estiver nulo, esse valor nulo substituirá o valor que estiver no banco
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var produtoPraVerificar = this.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoPraVerificar);
        Assertions.assertEquals(produto.getNome(), produtoPraVerificar.getNome());
        Assertions.assertEquals(produto.getDescricao(), produtoPraVerificar.getDescricao());
        Assertions.assertEquals(produto.getPreco(), produtoPraVerificar.getPreco());
    }

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
            .nome("Câmera Canon")
            .descricao("A melhor definição para suas fotos.")
            .preco(BigDecimal.valueOf(5000))
            .dataCriacao(LocalDateTime.now())
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(produto);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear(); // Limpa a memória do EntityManager. Obrigará o find() abaixo a ir no banco buscar, ao invés de pegar na metória do EntityManager.

        var produtoPraVerificar = this.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoPraVerificar);
    }
}

