package com.algaworks.junit;

import com.algaworks.ecommerce.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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
            .id(5)
            .nome("Smartphone One Plus")
            .descricao("O processador mais rápido.")
            .preco(BigDecimal.valueOf(2450))
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(produtoPersist);
        produtoPersist.setNome("Smartphone Two Plus"); // Essa alteração é efetivada mesmo após a operação de persistir.
        this.entityManager.getTransaction().commit();

        this.entityManager.clear(); // Limpa a memória do EntityManager. Obrigará o find() abaixo a ir no banco buscar, ao invés de pegar na metória do EntityManager.

        var produtoPraVerificar = this.entityManager.find(Produto.class, produtoPersist.getId());
        Assertions.assertNotNull(produtoPraVerificar);

        var produtoMerge = Produto.builder()
            .id(6)
            .nome("Notebook Dell XPS-9320")
            .descricao("O processador mais rápido.")
            .preco(BigDecimal.valueOf(11450))
            .build();

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
        var produto = Produto.builder()
            .id(4)
            .nome("Microfone Sony")
            .descricao("A melhor qualidade de som.")
            .preco(BigDecimal.valueOf(1050))
            .build();

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
        var produto = Produto.builder()
            .id(1)
            .nome("Kindle Paperwhite")
            .descricao("Novo Kindle")
            .preco(BigDecimal.valueOf(591.67))
            .build();

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

