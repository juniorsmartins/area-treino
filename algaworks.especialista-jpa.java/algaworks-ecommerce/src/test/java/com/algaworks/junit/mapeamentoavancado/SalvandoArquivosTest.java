package com.algaworks.junit.mapeamentoavancado;

import com.algaworks.ecommerce.model.NotaFiscal;
import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.model.Produto;
import com.algaworks.junit.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

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

        /**
        // Salvará o arquivo da notafiscal na minha pasta Home com o nome xml.pdf (ele salva na extensão que eu quiser, mas precisa ser a mesma do arquivo original)
        try(OutputStream out = new FileOutputStream(Files
                .createFile(Paths.get(System.getProperty("user.home") + "/xml.pdf")).toFile())) {
            out.write(notaFiscalVerificada.getXml());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        */
    }

    private static byte[] carregarNotaFiscal() throws IOException {

        try(var resposta = SalvandoArquivosTest.class.getResourceAsStream("/ArquivoPdfParaTeste.pdf")) {
            return resposta.readAllBytes();
        }
    }
}

