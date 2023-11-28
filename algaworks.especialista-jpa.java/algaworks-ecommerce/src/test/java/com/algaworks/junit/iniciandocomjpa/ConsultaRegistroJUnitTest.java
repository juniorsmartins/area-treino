package com.algaworks.junit.iniciandocomjpa;

import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConsultaRegistroJUnitTest extends EntityManagerTest {

    @Test
    void buscarPorIdentificador() {

        var produto = this.entityManager.find(Produto.class, 1);

        Assertions.assertNotNull(produto);
        Assertions.assertEquals("Kindle", produto.getNome());
    }

    @Test
    void atualizarReferencia() {

        var produto = this.entityManager.find(Produto.class, 1);
        produto.setNome("Microfone Samson");

        this.entityManager.refresh(produto); // reinicia a entidade do banco de dados. Ignora o setNome("Microfone Samson")

        Assertions.assertEquals("Kindle", produto.getNome());
    }
}

