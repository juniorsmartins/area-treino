package io.udemyapirestjava.config.bean;

import com.github.javafaker.Faker;
import io.udemyapirestjava.adapters.out.*;
import io.udemyapirestjava.application.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public PersonFindByIdFindByIdUseCase personFindByIdFindByIdUseCase(PersonFindByIdAdapter personFindByIdAdapter) {
        return new PersonFindByIdFindByIdUseCase(personFindByIdAdapter);
    }

    @Bean
    public PersonFindAllUseCase personFindAllUseCase(PersonFindAllAdapter personFindAllAdapter) {
        return new PersonFindAllUseCase(personFindAllAdapter);
    }

    @Bean
    public PersonCreateUseCase personCreateUseCase(PersonCreateAdapter personCreateAdapter) {
        return new PersonCreateUseCase(personCreateAdapter);
    }

    @Bean
    public PersonUpdateUseCase personUpdateUseCase(PersonFindByIdFindByIdUseCase personFindByIdFindByIdUseCase,
                                                   PersonUpdateAdapter personUpdateAdapter) {
        return new PersonUpdateUseCase(personFindByIdFindByIdUseCase, personUpdateAdapter);
    }

    @Bean
    public PersonDeleteUseCase personDeleteUseCase(PersonFindByIdFindByIdUseCase personFindByIdFindByIdUseCase,
                                                   PersonDeleteAdapter personDeleteAdapter) {
        return new PersonDeleteUseCase(personFindByIdFindByIdUseCase, personDeleteAdapter);
    }
}

