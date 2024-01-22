package com.algaworks.junit.concorrencia;

import com.algaworks.ecommerce.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class LockOtimistaTest {

    protected static EntityManagerFactory entityManagerFactory;

    @BeforeAll
    static void setUpClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    static void tearDownClass() {
        entityManagerFactory.close();
    }

    static void log(Object obj, Object... args) {
        System.out.println(String.format("[Log " + System.currentTimeMillis() + "]", args));
    }

    static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {}
    }

    @Test
    void usarLockOtimista() {

        // Criar
        Runnable runnable1 = () -> {
            EntityManager entityManager1 = entityManagerFactory.createEntityManager();
            entityManager1.getTransaction().begin();

            log("Runnable 01 carregará o produto 1.");
            var produto = entityManager1.find(Produto.class, 1);

            log("Runnable 01 esperará por 3 segundos.");
            esperar(3);

            log("Runnable 01 alterará o produto.");
            produto.setDescricao("Descrição detalhada");

            log("Runnable 01 confirmará a transação.");
            entityManager1.getTransaction().commit();
            entityManager1.clear();
        };

        // Criar
        Runnable runnable2 = () -> {
            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            entityManager2.getTransaction().begin();

            log("Runnable 02 carregará o produto 1.");
            var produto = entityManager2.find(Produto.class, 1);

            log("Runnable 02 esperará por 1 segundos.");
            esperar(1);

            log("Runnable 02 alterará o produto.");
            produto.setDescricao("Descrição massa");

            log("Runnable 02 confirmará a transação.");
            entityManager2.getTransaction().commit();
            entityManager2.clear();
        };

        // Instanciar
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        // Iniciar
        thread1.start();
        thread2.start();

        try {
            // Ponto de encontro
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        EntityManager entityManager3 = entityManagerFactory.createEntityManager();
        var produto = entityManager3.find(Produto.class, 1);
        entityManager3.close();

        Assertions.assertEquals("Descrição massa", produto.getDescricao());

        log("Encerrar método de teste");
    }
}

