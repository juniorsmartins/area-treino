package com.desafiov2picpayjava.config.beans;

import com.desafiov2picpayjava.adapters.out.UsuarioSalvarAdapter;
import com.desafiov2picpayjava.application.core.usecase.UsuarioCadastrarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    public UsuarioCadastrarUseCase usuarioCadastrarUseCase(UsuarioSalvarAdapter usuarioSalvarAdapter) {
        return new UsuarioCadastrarUseCase(usuarioSalvarAdapter);
    }
}

