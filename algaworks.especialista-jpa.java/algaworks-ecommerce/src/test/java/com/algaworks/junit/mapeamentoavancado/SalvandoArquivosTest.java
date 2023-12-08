package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;

class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    void salvarXmlNota() throws IOException {

        var pedido = super.entityManager.find(Pedido.class, 1);

        var notaFiscal = NotaFiscal.builder()
                .pedido(pedido)
                .dataEmissao(new Date())
                .xml(carregarNotaFiscal())
                .build();

        super.entityManager.getTransaction().begin();
        super.entityManager.persist(notaFiscal);
        super.entityManager.getTransaction().commit();

        super.entityManager.clear();

        var notaFiscalVerificada = super.entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assertions.assertNotNull(notaFiscalVerificada.getXml());
        Assertions.assertTrue(notaFiscalVerificada.getXml().length > 0);
    }

    private static byte[] carregarNotaFiscal() throws IOException {

        try(var resposta = SalvandoArquivosTest.class.getResourceAsStream("/ArquivoPdfParaTeste.pdf")) {
            return resposta.readAllBytes();
        }
    }
}

