package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.model.enums.SexoClienteEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "clientes", uniqueConstraints = { @UniqueConstraint(name = "unq_cpf", columnNames = {"cpf"}) },
        indexes = { @Index(name = "idx_nome", columnList = "nome")})
@SecondaryTable(name = "cliente_detalhe", pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"))
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners({ GenericoListener.class })
public final class Cliente extends EntidadeBaseInteger implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;

    private String cpf;

    @Transient
    private String primeiroNome;

    @Column(table = "cliente_detalhe")
    @Enumerated(EnumType.STRING)
    private SexoClienteEnum sexo;

    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @ElementCollection
    @CollectionTable(name = "cliente_contato",
        joinColumns = @JoinColumn(name = "cliente_id"))
    @MapKeyColumn(name = "tipo") // Define o nome da coluna da Chave do Map (Chave, Valor)
    @Column(name = "descricao") // Define o nome da coluna do Valor do Map (Chave, Valor)
    private Map<String, String> contatos;

    @PostLoad
    public void configurarPrimeiroNome() {
        if (nome != null && !nome.isBlank()) {
            int index = nome.indexOf(" ");
            if (index > -1) {
                primeiroNome = nome.substring(0, index);
            }
        }
    }
}

