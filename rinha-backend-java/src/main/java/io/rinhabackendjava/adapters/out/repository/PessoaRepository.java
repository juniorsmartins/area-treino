package io.rinhabackendjava.adapters.out.repository;

import io.rinhabackendjava.adapters.out.repository.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<PessoaEntity, UUID> { }

