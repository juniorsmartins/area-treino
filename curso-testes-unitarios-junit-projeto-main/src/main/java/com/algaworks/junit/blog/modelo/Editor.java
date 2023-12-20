package com.algaworks.junit.blog.modelo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public class Editor {
    private Long id;
    private String nome;
    private String email;
    private BigDecimal valorPagoPorPalavra;
    private boolean premium;
    private OffsetDateTime dataCriacao = OffsetDateTime.now();

    public Editor() { }

    public Editor(String nome, String email, BigDecimal valorPagoPorPalavra, boolean premium) {
        this(null, nome, email, valorPagoPorPalavra, premium);
    }

    public Editor(Long id, String nome, String email, BigDecimal valorPagoPorPalavra, boolean premium) {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(email);
        Objects.requireNonNull(valorPagoPorPalavra);
        this.id = id; //Pode ser nulo, caso seja um editor novo
        this.nome = nome;
        this.email = email;
        this.valorPagoPorPalavra = valorPagoPorPalavra;
        this.premium = premium;
    }

    public void atualizarComDados(Editor editor) {
        Objects.requireNonNull(editor);
        this.nome = editor.nome;
        this.email = editor.email;
        this.valorPagoPorPalavra = editor.valorPagoPorPalavra;
        this.premium = editor.premium;
    }

    public static Builder builder() {
        return new Builder();
    }

    public final static class Builder {

        private Long id;
        private String nome;
        private String email;
        private BigDecimal valorPagoPorPalavra;
        private boolean premium;

        private Builder() { }

        public Builder comId(Long id) {
            this.id = id;
            return this;
        }

        public Builder comNome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder comEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder comValorPagoPorPalavra(BigDecimal valorPagoPorPalavra) {
            this.valorPagoPorPalavra = valorPagoPorPalavra;
            return this;
        }

        public Builder comPremium(boolean premium) {
            this.premium = premium;
            return this;
        }

        public Editor build() {
            return new Editor(this.id, this.nome, this.email, this.valorPagoPorPalavra, this.premium);
        }
    }
}
