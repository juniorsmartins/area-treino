package io.udemyapirestjava.config;

import io.udemyapirestjava.application.core.usecase.MultiplicarUseCase;
import io.udemyapirestjava.application.core.usecase.UtilUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiplicarConfig {

    @Bean
    public MultiplicarUseCase multiplicarUseCase(UtilUseCase utilUseCase) {
        return new MultiplicarUseCase(utilUseCase);
    }
}

