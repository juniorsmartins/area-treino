package com.algaworks.ecommerce.service;

import com.algaworks.ecommerce.model.Pedido;

public class NotaFiscalService {

    public void gerar(Pedido pedido) {
        System.out.println("\n\nGerando nota fiscal para o pedido " + pedido.getId() + ".\n\n");
    }
}

