package io.rinhabackendjava.adapters.in.controller.response;

import java.util.HashSet;
import java.util.UUID;

public record PessoaResponse(

        UUID id,

        String apelido,

        String nome,

        String nascimento,

        HashSet<String> stack
) { }

