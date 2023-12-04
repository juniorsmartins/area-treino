package com.algaworks.junit.conhecendoentitymanager;

import com.algaworks.ecommerce.model.Categoria;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Test;

class EstadosECicloDeVidaTest extends EntityManagerTest {

    @Test
    void analisarEstados() {

        var categoriaNovo = new Categoria(); // Estado transient
        var categoriaNovaGerenciada = this.entityManager.merge(categoriaNovo); // O retorno é Estado gerenciado

        var categoriaGerenciado = this.entityManager.find(Categoria.class, 1); // Estado gerenciado

        this.entityManager.remove(categoriaGerenciado); // Estado remove

        this.entityManager.persist(categoriaGerenciado); // Volta para o Estado gerenciado - antes do fim da transação

        this.entityManager.detach(categoriaGerenciado); // Estado desanexado
    }
}

