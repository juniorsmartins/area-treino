package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "pagamentos_cartao")
@DiscriminatorValue("cartao")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class PagamentoCartao extends Pagamento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "numero_cartao", length = 50)
    private String numeroCartao;
}

