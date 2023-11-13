package com.desafiov2picpayjava.config.beans;

import com.desafiov2picpayjava.adapters.out.CarteiraBuscarPorIdAdapter;
import com.desafiov2picpayjava.adapters.out.CarteiraSalvarAdapter;
import com.desafiov2picpayjava.adapters.out.TransferenciaSalvarAdapter;
import com.desafiov2picpayjava.application.core.usecase.TransferenciaEfetuarUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferenciaConfig {

    @Bean
    public TransferenciaEfetuarUseCase transferenciaEfetuarUseCase(TransferenciaSalvarAdapter transferenciaSalvarAdapter,
                                                                   CarteiraBuscarPorIdAdapter carteiraBuscarPorIdAdapter,
                                                                   CarteiraSalvarAdapter carteiraSalvarAdapter) {
        return new TransferenciaEfetuarUseCase(transferenciaSalvarAdapter, carteiraBuscarPorIdAdapter, carteiraSalvarAdapter);
    }
}

