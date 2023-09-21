package io.rinhabackendjava.adapters.out.repository.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

public class PessoaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    private String apelido;

    private String nome;

    private String nascimento;

    private HashSet<String> stack;
}

