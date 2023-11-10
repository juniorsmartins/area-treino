package com.desafiov2picpayjava.config.beans;

import com.desafiov2picpayjava.adapters.out.CarteiraSalvarAdapter;
import com.desafiov2picpayjava.application.core.usecase.CarteiraCadastrarUseCase;
import com.desafiov2picpayjava.application.core.usecase.UsuarioBuscarPorIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarteiraConfig {

    @Bean
    public CarteiraCadastrarUseCase carteiraCadastrarUseCase(UsuarioBuscarPorIdUseCase usuarioBuscarPorIdUseCase,
                                                             CarteiraSalvarAdapter carteiraSalvarAdapter) {
        return new CarteiraCadastrarUseCase(usuarioBuscarPorIdUseCase, carteiraSalvarAdapter);
    }
}

