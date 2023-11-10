package com.desafiov2picpayjava.application.core.usecase.utils;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

public final class UtilsImpl implements Utils {

    @Override
    public String capitalizarTexto(String texto) {

        return Optional.of(texto)
            .map(String::trim)
            .map(WordUtils::capitalizeFully)
            .map(escrito -> RegExUtils.replaceAll(escrito, " Da ", " da "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " De ", " de "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Di ", " di "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Do ", " do "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Du ", " du "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Em ", " em "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Um ", " um "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Na ", " na "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Ne ", " ne "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Ni ", " ni "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " No ", " no "))
            .map(escrito -> RegExUtils.replaceAll(escrito, " Nu ", " nu "))
            .map(escrito -> RegExUtils.replaceAll(escrito, "Da ", "da "))
            .map(escrito -> RegExUtils.replaceAll(escrito, "De ", "de "))
            .orElseThrow(NoSuchElementException::new);
    }
}

