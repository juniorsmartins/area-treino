package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.Atributo;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class ElementCollectionTest extends EntityManagerTest {

    @Test
    void aplicarTags() {

        super.entityManager.getTransaction().begin();

        var produto = super.entityManager.find(Produto.class, 1);
        produto.setTags(List.of("Ebook", "livro-digital"));

        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var produtoVerificado = super.entityManager.find(Produto.class, produto.getId());
        Assertions.assertNotNull(produtoVerificado);
        Assertions.assertEquals(2, produtoVerificado.getTags().size());
    }

    @Test
    void aplicarAtributos() {

        super.entityManager.getTransaction().begin();

        var produto = super.entityManager.find(Produto.class, 3);

        Assertions.assertTrue(produto.getAtributos().isEmpty());

        var atributo1 = Atributo.builder()
                .nome("Cor")
                .valor("Branca")
                .build();

        var atributo2 = Atributo.builder()
                .nome("Quantia de Páginas")
                .valor("650")
                .build();

        produto.setAtributos(List.of(atributo1, atributo2));

        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var produtoVerificado = super.entityManager.find(Produto.class, produto.getId());
        Assertions.assertFalse(produtoVerificado.getAtributos().isEmpty());
    }

    @Test
    void aplicarContatos() {

        super.entityManager.getTransaction().begin();

        var cliente = super.entityManager.find(Cliente.class, 3);
        Assertions.assertTrue(cliente.getContatos().isEmpty());

        cliente.setContatos(Map.of("Telefone", "6533324455", "Email", "teste@email.com", "Celular", "6599993344"));

        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var clienteVerificado = super.entityManager.find(Cliente.class, cliente.getId());
        Assertions.assertFalse(clienteVerificado.getContatos().isEmpty());
        Assertions.assertEquals("teste@email.com", clienteVerificado.getContatos().get("Email"));
    }
}

