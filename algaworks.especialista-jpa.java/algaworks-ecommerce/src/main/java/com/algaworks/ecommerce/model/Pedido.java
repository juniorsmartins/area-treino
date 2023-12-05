package com.algaworks.ecommerce.model;

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
@ToString
@EqualsAndHashCode(of = {"id"})
@EntityListeners({ GerarNotaFiscalListener.class })
public final class Pedido implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido") // Padrão JPA é Lazy para listas/plural. E Eager para valor singular.
    private List<ItemPedido> itensPedido;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @OneToOne(mappedBy = "pedido")
    private PagamentoCartao pagamentoCartao;

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

