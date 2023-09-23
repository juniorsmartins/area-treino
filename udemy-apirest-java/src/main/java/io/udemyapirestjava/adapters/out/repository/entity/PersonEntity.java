package io.udemyapirestjava.adapters.out.repository.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "persons")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
public final class PersonEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "gender", nullable = false, length = 6)
    private String gender;
}

