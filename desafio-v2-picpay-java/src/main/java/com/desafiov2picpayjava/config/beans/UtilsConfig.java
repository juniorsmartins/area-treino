package com.desafiov2picpayjava.config.beans;

import com.desafiov2picpayjava.application.core.usecase.utils.UtilsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilsConfig {

    @Bean
    public UtilsImpl utilsImpl() {
        return new UtilsImpl();
    }
}

