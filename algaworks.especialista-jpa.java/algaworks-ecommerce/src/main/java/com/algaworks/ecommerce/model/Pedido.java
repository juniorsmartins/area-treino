package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners({ GerarNotaFiscalListener.class, GenericoListener.class })
public final class Pedido extends EntidadeBaseInteger implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "status", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    // Pedido é mandante - Ao usar CascadeType.PERSIST, quando Pedido for salvo, também salvará automático os ItensPedido.
    @OneToMany(mappedBy = "pedido", cascade = {CascadeType.PERSIST}) // Padrão JPA é Lazy para listas/plural. E Eager para valor singular.
    private List<ItemPedido> itensPedido;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    public boolean isPago() {
        return StatusPedidoEnum.PAGO.equals(status);
    }

    @PrePersist // Callback
    public void aoPersistir() {
        this.dataCriacao = LocalDateTime.now();
        this.calcularTotal();
    }

    @PreUpdate
    public void aoAtualizar() {
        this.dataUltimaAtualizacao = LocalDateTime.now();
        this.calcularTotal();
    }

//    @PrePersist
//    @PreUpdate
    public void calcularTotal() {
        if (itensPedido != null) {
            this.total = itensPedido.stream()
                .map(ItemPedido::getPrecoProduto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }
}

