package com.algaworks.ecommerce.listener;

import jakarta.persistence.PostLoad;

public class GenericoListener {

    @PostLoad
    public void logCarregamento(Object obj) {
        System.out.println("\n\nEntidade " + obj.getClass().getSimpleName() + " foi carregada.\n\n");
    }
}

