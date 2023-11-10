package com.desafiov2picpayjava.application.core.domain;

import com.desafiov2picpayjava.application.core.domain.enums.TipoUsuarioEnum;
import com.desafiov2picpayjava.application.core.domain.value_objects.CorreioEletronico;

public final class Usuario {

    private long version;

    private Long id;

    private String nome;

    private String documento;

    private CorreioEletronico email;

    private String senha;

    private TipoUsuarioEnum tipo;

    private Carteira carteira;

    public Usuario() {}

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return this.email.getEmail();
    }

    public void setEmail(String email) {
        this.email = new CorreioEletronico(email);
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuarioEnum getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }

    public Carteira getCarteira() {
        return this.carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }
}

