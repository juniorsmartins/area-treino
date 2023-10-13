package io.udemyapirestjava.config.bean;

import io.udemyapirestjava.adapters.out.*;
import io.udemyapirestjava.application.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    public PersonFindByIdUseCase personFindByIdUseCase(PersonFindByIdAdapter personFindByIdAdapter) {
        return new PersonFindByIdUseCase(personFindByIdAdapter);
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
    public PersonUpdateUseCase personUpdateUseCase(PersonFindByIdUseCase personFindByIdUseCase,
                                                   PersonUpdateAdapter personUpdateAdapter) {
        return new PersonUpdateUseCase(personFindByIdUseCase, personUpdateAdapter);
    }

    @Bean
    public PersonDeleteUseCase personDeleteUseCase(PersonFindByIdUseCase personFindByIdUseCase,
                                                   PersonDeleteAdapter personDeleteAdapter) {
        return new PersonDeleteUseCase(personFindByIdUseCase, personDeleteAdapter);
    }
}

