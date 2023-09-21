package io.rinhabackendjava.application.core.domain;

import java.util.HashSet;
import java.util.UUID;

public class Pessoa {

    private UUID id;

    private String apelido;

    private String nome;

    private String nascimento;

    private HashSet<String> stack;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public HashSet<String> getStack() {
        return stack;
    }

    public void setStack(HashSet<String> stack) {
        this.stack = stack;
    }
}

