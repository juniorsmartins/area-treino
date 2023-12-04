package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> consultaDinamicaComCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFretefinal) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder(); // O CriteriaBuilder é uma fábrica para construir elementos de consulta, como o próprio criteria.
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class); // O CriteriaQuery é um construtor de cláusulas de consulta
        Root<Restaurante> root = criteria.from(Restaurante.class);

        var predicates = new ArrayList<Predicate>();

        if (StringUtils.hasText(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (Objects.nonNull(taxaFreteInicial)) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if (Objects.nonNull(taxaFretefinal)) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFretefinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurante> typedQuery = this.entityManager.createQuery(criteria);

        return typedQuery.getResultList();
    }

    @Override
    public List<Restaurante> consultaDinamicaComJpql(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        var jpql = new StringBuilder();
        jpql.append("from Restaurante where 0 = 0 ");

        var parametros = new HashMap<String, Object>();

        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaFreteInicial != null) {
            jpql.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }

        if (taxaFreteFinal != null) {
            jpql.append("and taxaFrete <= :taxaFinal");
            parametros.put("taxaFinal", taxaFreteFinal);
        }

        var typedQuery = this.entityManager.createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach(typedQuery::setParameter);

        return typedQuery.getResultList();
    }
}

