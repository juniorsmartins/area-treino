package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listener.GenericoListener;
import com.algaworks.ecommerce.listener.GerarNotaFiscalListener;
import com.algaworks.ecommerce.model.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.engine.spi.PersistentAttributeInterceptable;
import org.hibernate.engine.spi.PersistentAttributeInterceptor;

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
public final class Pedido extends EntidadeBaseInteger implements Serializable, PersistentAttributeInterceptable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @PastOrPresent
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PastOrPresent
    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @PastOrPresent
    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @NotNull
    @Positive
    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal total;

    @NotNull
    @Column(name = "status", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    // Pedido é mandante - Ao usar CascadeType.PERSIST, quando Pedido for salvo, também salvará automático os ItensPedido.
    @NotEmpty
    @OneToMany(mappedBy = "pedido") // Padrão JPA é Lazy para listas/plural. E Eager para valor singular.
    private List<ItemPedido> itensPedido;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToOne(mappedBy = "pedido", fetch = FetchType.LAZY)
    private NotaFiscal notaFiscal;

    @LazyToOne(LazyToOneOption.NO_PROXY)
    @OneToOne(mappedBy = "pedido", fetch = FetchType.LAZY)
    private Pagamento pagamento;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @Transient
    private PersistentAttributeInterceptor persistentAttributeInterceptor;

    @Override
    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
        return this.persistentAttributeInterceptor;
    }

    @Override
    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor interceptor) {
        this.persistentAttributeInterceptor = interceptor;
    }

    public NotaFiscal getNotaFiscal() {
        if (this.persistentAttributeInterceptor != null) {
            return (NotaFiscal) this.persistentAttributeInterceptor.readObject(this, "notaFiscal", this.notaFiscal);
        }
        return this.notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        if (this.persistentAttributeInterceptor != null) {
            this.notaFiscal = (NotaFiscal) this.persistentAttributeInterceptor
                .writeObject(this, "notaFiscal", this.notaFiscal, notaFiscal);
        } else {
            this.notaFiscal = notaFiscal;
        }
    }

    public Pagamento getPagamento() {
        if (this.persistentAttributeInterceptor != null) {
            return (Pagamento) this.persistentAttributeInterceptor.readObject(this, "pagamento", this.pagamento);
        }
        return this.pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        if (this.persistentAttributeInterceptor != null) {
            this.pagamento = (Pagamento) this.persistentAttributeInterceptor
                    .writeObject(this, "pagamento", this.pagamento, pagamento);
        } else {
            this.pagamento = pagamento;
        }
    }

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
                .map(item -> new BigDecimal(item.getQuantidade()).multiply(item.getPrecoProduto()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            total = BigDecimal.ZERO;
        }
    }
}

