package io.udemyapirestjava.config.bean;

import com.github.javafaker.Faker;
import io.udemyapirestjava.application.core.usecase.PersonCreateUseCase;
import io.udemyapirestjava.application.core.usecase.PersonFindAllUseCase;
import io.udemyapirestjava.application.core.usecase.PersonFindByIdFindByIdUseCase;
import io.udemyapirestjava.application.core.usecase.PersonUpdateUseCase;
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
    public PersonCreateUseCase personCreateUseCase() {
        return new PersonCreateUseCase();
    }

    @Bean
    public PersonUpdateUseCase personUpdateUseCase() {
        return new PersonUpdateUseCase();
    }
}
