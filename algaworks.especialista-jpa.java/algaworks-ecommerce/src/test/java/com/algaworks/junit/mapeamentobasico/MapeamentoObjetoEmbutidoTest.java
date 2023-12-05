package com.algaworks.junit.mapeamentobasico;

import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.EnderecoEntregaPedido;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    void analisarMapeamentoObjetoEmbutido() {

        var cliente = this.entityManager.find(Cliente.class, 5);

        var enderecoEntrega = EnderecoEntregaPedido.builder()
            .cep("78000000")
            .estado("Bahia")
            .cidade("Salvador")
            .bairro("Trem Fumaça")
            .logradouro("Avenida Bohemia")
            .numero("500")
            .complemento("Entrada pela porta lateral")
            .build();

        var pedido = Pedido.builder()
            .status(StatusPedidoEnum.AGUARDANDO)
            .total(BigDecimal.valueOf(1000))
            .enderecoEntrega(enderecoEntrega)
            .cliente(cliente)
            .build();

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(pedido);
        this.entityManager.getTransaction().commit();

        this.entityManager.clear();

        var pedidoVerificacao = this.entityManager.find(Pedido.class, pedido.getId());
        Assertions.assertNotNull(pedidoVerificacao);
        Assertions.assertNotNull(pedidoVerificacao.getEnderecoEntrega());
        Assertions.assertNotNull(pedidoVerificacao.getEnderecoEntrega().getCep());
    }
}

