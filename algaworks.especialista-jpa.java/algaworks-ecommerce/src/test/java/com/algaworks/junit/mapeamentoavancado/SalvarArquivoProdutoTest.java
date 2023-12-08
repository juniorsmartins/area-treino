package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

class SalvarArquivoProdutoTest extends EntityManagerTest {

    @Test
    void carregarFoto() throws IOException {
        super.entityManager.getTransaction().begin();

        var produto = Produto.builder()
                .nome("Notebook DELL XPS-9320")
                .preco(BigDecimal.valueOf(12000))
                .descricao("Processador i7 de 13 geração")
                .dataCriacao(LocalDateTime.now())
                .foto(pegarFoto())
                .build();

        super.entityManager.persist(produto);

        super.entityManager.getTransaction().commit();
        super.entityManager.clear();

        var produtoVerificado = super.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificado.getFoto());
    }

    private static byte[] pegarFoto() throws IOException {

        try(var inputStream = SalvarArquivoProdutoTest.class.getResourceAsStream("/dell-xps-9320.png")) {
            return Objects.requireNonNull(inputStream).readAllBytes();
        }
    }
}

