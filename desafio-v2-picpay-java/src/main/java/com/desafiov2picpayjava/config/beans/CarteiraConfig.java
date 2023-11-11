package com.desafiov2picpayjava.config.beans;

import com.desafiov2picpayjava.adapters.out.CarteiraBuscarPorIdAdapter;
import com.desafiov2picpayjava.adapters.out.CarteiraSalvarAdapter;
import com.desafiov2picpayjava.application.core.usecase.CarteiraBuscarPorIdUseCase;
import com.desafiov2picpayjava.application.core.usecase.CarteiraCadastrarUseCase;
import com.desafiov2picpayjava.application.core.usecase.CarteiraDepositarUseCase;
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

    @Bean
    public CarteiraBuscarPorIdUseCase carteiraBuscarPorIdUseCase(CarteiraBuscarPorIdAdapter carteiraBuscarPorIdAdapter) {
        return new CarteiraBuscarPorIdUseCase(carteiraBuscarPorIdAdapter);
    }

    @Bean
    public CarteiraDepositarUseCase carteiraDepositarUseCase(CarteiraBuscarPorIdUseCase carteiraBuscarPorIdUseCase,
                                                             CarteiraSalvarAdapter carteiraSalvarAdapter) {
        return new CarteiraDepositarUseCase(carteiraBuscarPorIdUseCase, carteiraSalvarAdapter);
    }
}

