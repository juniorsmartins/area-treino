package io.udemyapirestjava.config.bean;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaFakerConfig {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}

