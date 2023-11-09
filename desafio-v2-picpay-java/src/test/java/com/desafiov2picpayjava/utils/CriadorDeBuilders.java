package com.desafiov2picpayjava.utils;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioDtoIn;
import com.desafiov2picpayjava.application.core.domain.enums.TipoUsuarioEnum;
import com.github.javafaker.Faker;

import java.util.Random;

public final class CriadorDeBuilders {

    private static final Faker faker = new Faker();

    private static final GeradorCpf geradorCpf = new GeradorCpf();

    private static final Random random = new Random();

    public static UsuarioDtoIn.UsuarioDtoInBuilder gerarUsuarioDtoInBuilder() {

        return UsuarioDtoIn.builder()
            .nome(faker.name().fullName())
            .documento(geradorCpf.cpf(false))
            .email(faker.internet().emailAddress())
            .senha(faker.lorem().characters(5, 10))
            .tipo(TipoUsuarioEnum.COMUM.getTipo());
    }
}

