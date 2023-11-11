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

    public Usuario() {}

    public String getEmail() {
        return this.email.getEmail();
    }

    public void setEmail(String email) {
        this.email = new CorreioEletronico(email);
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setEmail(CorreioEletronico email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuarioEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "version=" + version +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", email=" + email +
                ", senha='" + senha + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}

