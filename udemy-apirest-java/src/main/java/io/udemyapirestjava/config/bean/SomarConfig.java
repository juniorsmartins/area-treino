package io.udemyapirestjava.config.bean;

import io.udemyapirestjava.application.core.usecase.SomarUseCase;
import io.udemyapirestjava.application.core.usecase.UtilUseCase;
import io.udemyapirestjava.application.ports.in.UtilInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SomarConfig {

    @Bean
    public SomarUseCase somarUseCase(UtilUseCase utilUseCase) {
        return new SomarUseCase(utilUseCase);
    }
}

