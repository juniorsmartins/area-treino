package io.udemyapirestjava.config;

import io.udemyapirestjava.application.core.usecase.DividirUseCase;
import io.udemyapirestjava.application.core.usecase.UtilUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DividirConfig {

    @Bean
    public DividirUseCase dividirUseCase(UtilUseCase utilUseCase) {
        return new DividirUseCase(utilUseCase);
    }
}

