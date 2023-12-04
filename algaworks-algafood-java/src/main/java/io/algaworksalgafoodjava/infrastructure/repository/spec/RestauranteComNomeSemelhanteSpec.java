package io.algaworksalgafoodjava.infrastructure.repository.spec;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@RequiredArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

    private final String nome;

    @Override
    public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        return criteriaBuilder.like(root.get("nome"), "%" + this.nome + "%");
    }
}

