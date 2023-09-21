package io.udemyapirestjava.config.bean;

import com.github.javafaker.Faker;
import io.udemyapirestjava.application.core.usecase.PersonCreateUseCase;
import io.udemyapirestjava.application.core.usecase.PersonFindAllUseCase;
import io.udemyapirestjava.application.core.usecase.PersonFindByIdFindByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public PersonFindByIdFindByIdUseCase personFindByIdFindByIdUseCase() {
        return new PersonFindByIdFindByIdUseCase();
    }

    @Bean
    public PersonFindAllUseCase personFindAllUseCase(Faker faker) {
        return new PersonFindAllUseCase(faker);
    }

    @Bean
    public PersonCreateUseCase personCreateUseCase(Faker faker) {
        return new PersonCreateUseCase(faker);
    }
}

