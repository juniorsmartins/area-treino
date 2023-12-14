package io.algaworksalgafoodjava.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable // Classe incorporável - é parte de uma entidade
public final class Endereco implements Serializable {

    @Column(name = "endereco_cep", length = 15, nullable = false)
    private String cep;

    @Column(name = "endereco_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "endereco_numero", length = 10)
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_bairro", length = 50, nullable = false)
    private String bairro;

    // Mandante Owner
    @ManyToOne
    @JoinColumn(name = "endereco_cidade_id", nullable = false)
    private Cidade cidade;
}

