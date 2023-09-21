package io.rinhabackendjava.adapters.in.controller.request;

import java.util.HashSet;

public record PessoaRequest(

        String apelido,

        String nome,

        String nascimento,

        HashSet<String> stack
) { }

