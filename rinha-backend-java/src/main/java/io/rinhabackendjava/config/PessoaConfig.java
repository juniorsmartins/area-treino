package io.rinhabackendjava.config;

import io.rinhabackendjava.adapters.out.PessoaCreateAdapter;
import io.rinhabackendjava.application.core.usecase.PessoaCreateUseCase;
import io.rinhabackendjava.application.ports.out.PessoaCreateOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaConfig {

    @Bean
    public PessoaCreateUseCase pessoaCreateUseCase(PessoaCreateAdapter pessoaCreateAdapter) {
        return new PessoaCreateUseCase(pessoaCreateAdapter);
    }
}

