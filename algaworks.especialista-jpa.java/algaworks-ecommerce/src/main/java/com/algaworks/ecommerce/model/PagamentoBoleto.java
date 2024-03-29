package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "pagamentos_boleto")
@DiscriminatorValue("boleto")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class PagamentoBoleto extends Pagamento implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Column(name = "codigo_barras", length = 100)
    private String codigoBarras;
}

