package io.udemyapirestjava.config;

import io.udemyapirestjava.application.core.usecase.SubtrairUseCase;
import io.udemyapirestjava.application.core.usecase.UtilUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubtrairConfig {

    @Bean
    public SubtrairUseCase subtrairUseCase(UtilUseCase utilUseCase) {
        return new SubtrairUseCase(utilUseCase);
    }
}

