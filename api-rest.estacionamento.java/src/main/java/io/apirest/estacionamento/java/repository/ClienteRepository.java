package io.apirest.estacionamento.java.repository;

import io.apirest.estacionamento.java.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }

