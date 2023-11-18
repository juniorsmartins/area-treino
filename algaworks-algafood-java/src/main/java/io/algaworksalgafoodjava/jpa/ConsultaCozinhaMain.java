package io.algaworksalgafoodjava.jpa;

import io.algaworksalgafoodjava.AlgaworksAlgafoodJavaApplication;
import io.algaworksalgafoodjava.domain.model.Cozinha;
import org.hibernate.result.Output;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaworksAlgafoodJavaApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
        List<Cozinha> lista = cadastroCozinha.listar();

        for (Cozinha cozinha : lista) {
            System.out.println(cozinha.getNome());
        }
    }
}

