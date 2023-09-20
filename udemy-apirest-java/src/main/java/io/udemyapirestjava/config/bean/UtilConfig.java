package io.udemyapirestjava.config.bean;

import io.udemyapirestjava.application.core.usecase.UtilUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public UtilUseCase utilUseCase() {
        return new UtilUseCase();
    }
}

