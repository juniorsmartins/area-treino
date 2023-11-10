package com.desafiov2picpayjava.adapters.out.entities;

import com.desafiov2picpayjava.application.core.domain.enums.TipoUsuarioEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class UsuarioOrm implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Version
//    @org.hibernate.annotations.Generated(GenerationTime.ALWAYS)
    private long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "documento", nullable = false, unique = true, length = 14)
    private String documento;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "senha", nullable = false, length = 30)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoUsuarioEnum tipo;

    @OneToOne
    @JoinColumn(name = "carteira_id", referencedColumnName = "id")
    private CarteiraOrm carteira;
}

