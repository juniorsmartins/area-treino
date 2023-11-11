package com.desafiov2picpayjava.config.beans;

import com.desafiov2picpayjava.adapters.out.UsuarioBuscarPorIdAdapter;
import com.desafiov2picpayjava.adapters.out.UsuarioBuscarTodosAdapter;
import com.desafiov2picpayjava.adapters.out.UsuarioSalvarAdapter;
import com.desafiov2picpayjava.application.core.usecase.UsuarioBuscarPorIdUseCase;
import com.desafiov2picpayjava.application.core.usecase.UsuarioBuscarTodosUseCase;
import com.desafiov2picpayjava.application.core.usecase.UsuarioCadastrarUseCase;
import com.desafiov2picpayjava.application.core.usecase.utils.UtilsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfig {

    @Bean
    public UsuarioCadastrarUseCase usuarioCadastrarUseCase(UsuarioSalvarAdapter usuarioSalvarAdapter,
                                                           UtilsImpl utilsImpl) {
        return new UsuarioCadastrarUseCase(usuarioSalvarAdapter, utilsImpl);
    }

    @Bean
    public UsuarioBuscarPorIdUseCase usuarioBuscarPorIdUseCase(UsuarioBuscarPorIdAdapter usuarioBuscarPorIdAdapter) {
        return new UsuarioBuscarPorIdUseCase(usuarioBuscarPorIdAdapter);
    }

    @Bean
    public UsuarioBuscarTodosUseCase usuarioBuscarTodosUseCase(UsuarioBuscarTodosAdapter usuarioBuscarTodosAdapter) {
        return new UsuarioBuscarTodosUseCase(usuarioBuscarTodosAdapter);
    }
}

