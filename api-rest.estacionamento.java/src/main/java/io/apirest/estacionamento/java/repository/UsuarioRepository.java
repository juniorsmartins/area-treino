package io.apirest.estacionamento.java.repository;

import io.apirest.estacionamento.java.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { }

