package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

class DetalheColumnTest extends EntityManagerTest {

    @Test
    void impedirInsercaoNaColunaDataUltimaAtualizacao() {

        var produto = Produto.builder()
            .nome("Notebook Dell XPS-9320")
            .descricao("Processador i7-1360P geração 13th com 32Gb Ram")
            .preco(BigDecimal.valueOf(12000.00))
            .dataCriacao(LocalDateTime.now())
            .dataUltimaAtualizacao(LocalDateTime.now())
            .build();

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(produto);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var produtoVerificar = super.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificar.getDataCriacao());
        Assertions.assertNull(produtoVerificar.getDataUltimaAtualizacao());
    }

    @Test
    void impedirAtualizacaoNaColunaDataCriacao() {

        var produto = super.entityManager.find(Produto.class, 1);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(produto);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var produtoVerificar = super.entityManager.find(Produto.class, produto.getId());

        Assertions.assertNotEquals(produtoVerificar.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
                produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));

        Assertions.assertEquals(produtoVerificar.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
                produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
    }
}

