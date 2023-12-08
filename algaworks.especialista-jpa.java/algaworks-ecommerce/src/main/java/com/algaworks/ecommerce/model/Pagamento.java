package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.model.enums.StatusPagamentoEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "pagamento")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract non-sealed class Pagamento extends EntidadeBaseInteger implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum status;
}

