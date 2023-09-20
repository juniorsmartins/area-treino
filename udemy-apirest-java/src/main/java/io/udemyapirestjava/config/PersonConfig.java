package io.udemyapirestjava.config;

import io.udemyapirestjava.application.core.usecase.PersonFindByIdFindByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public PersonFindByIdFindByIdUseCase personFindByIdFindByIdUseCase() {
        return new PersonFindByIdFindByIdUseCase();
    }
}

