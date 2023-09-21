package io.rinhabackendjava.adapters.out.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

@Entity
@Table(name = "pessoas")
@Getter
@Setter
public class PessoaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;

    private String apelido;

    private String nome;

    private String nascimento;

    private HashSet<String> stack;
}

