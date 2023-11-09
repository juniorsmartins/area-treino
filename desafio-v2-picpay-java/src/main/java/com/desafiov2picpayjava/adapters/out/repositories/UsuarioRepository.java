package com.desafiov2picpayjava.adapters.out.repositories;

import com.desafiov2picpayjava.adapters.out.entities.UsuarioOrm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioOrm, Long> { }

