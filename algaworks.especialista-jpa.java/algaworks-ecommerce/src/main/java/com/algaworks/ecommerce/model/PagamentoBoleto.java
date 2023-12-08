package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Boleto")
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

    @Column(name = "codigo_barras")
    private String codigoBarras;
}

