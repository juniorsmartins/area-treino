package com.algaworks.junit.jpql;

import com.algaworks.ecommerce.dto.ProdutoDto;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class BasicoJPQLTest extends EntityManagerTest {

    @Test
    void projetarResultadoNoDto() {
        String jpql = "select new com.algaworks.ecommerce.dto.ProdutoDto(id, nome) from Produto";

        TypedQuery<ProdutoDto> typedQuery = super.entityManager.createQuery(jpql, ProdutoDto.class);
        List<ProdutoDto> lista = typedQuery.getResultList();

        lista.forEach(System.out::println);

        Assertions.assertEquals(3, lista.size());
    }

    @Test
    void projetarResultado() {
        String jpql = "select id, nome from Produto";

        TypedQuery<Object[]> typedQuery = super.entityManager.createQuery(jpql, Object[].class);
        List<Object[]> lista = typedQuery.getResultList();

        Assertions.assertEquals(2, lista.get(0).length);
        Assertions.assertEquals(3, lista.size());
    }

    @Test
    void selecionarUmAtributoParaRetorno() {
        String jpql = "select p.nome from Produto p";
        TypedQuery<String> typedQuery = super.entityManager.createQuery(jpql, String.class);
        List<String> lista = typedQuery.getResultList();
        Assertions.assertEquals(String.class, lista.get(0).getClass());

        String jpqlCliente = "select p.cliente from Pedido p";
        TypedQuery<Cliente> typedQueryCliente = super.entityManager.createQuery(jpqlCliente, Cliente.class);
        List<Cliente> listaCliente = typedQueryCliente.getResultList();
        Assertions.assertEquals(Cliente.class, listaCliente.get(0).getClass());
    }

    @Test
    void buscarPorIdentificador() {
        // entityManager.find(Pedido.class, 1);
        TypedQuery<Pedido> typedQuery = entityManager.createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = typedQuery.getSingleResult();

        Assertions.assertNotNull(pedido);
    }

    @Test
    void mostrarDiferencaQueries() {
        String jpql = "select p from Pedido p where p.id = 1";

        TypedQuery<Pedido> typedQuery = super.entityManager.createQuery(jpql, Pedido.class); // Jpa versão mais nova
        Pedido pedido1 = typedQuery.getSingleResult();
        Assertions.assertNotNull(pedido1);

        Query query = super.entityManager.createQuery(jpql); // Jpa varsão mais antiga
        Pedido pedido2 = (Pedido) query.getSingleResult(); // A diferença entre as duas é basicamente a necessidade de conversão
        Assertions.assertNotNull(pedido2);
        // Ou - pra eliminar a conversão explícita - porém, acende warnning
        List<Pedido> lista = query.getResultList();
        Assertions.assertFalse(lista.isEmpty());
    }
}

