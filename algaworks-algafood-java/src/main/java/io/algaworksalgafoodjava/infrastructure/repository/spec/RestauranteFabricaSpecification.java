package io.algaworksalgafoodjava.infrastructure.repository.spec;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteFabricaSpecification {

    public static Specification<Restaurante> comFreteGratis() {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> comNomeSemelhante(String nome) {
        return (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}

