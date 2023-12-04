package io.algaworksalgafoodjava.domain.repository;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> { }

