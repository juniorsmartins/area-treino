package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurante> consultaDinamicaComCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFretefinal) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder(); // O CriteriaBuilder é uma fábrica para construir elementos de consulta, como o próprio criteria.
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class); // O CriteriaQuery é um construtor de cláusulas de consulta
        criteria.from(Restaurante.class);

        TypedQuery<Restaurante> typedQuery = this.entityManager.createQuery(criteria);

        return typedQuery.getResultList();
    }

    public List<Restaurante> consultaDinamicaComJpql(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFretefinal) {

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

        if (taxaFretefinal != null) {
            jpql.append("and taxaFrete <= :taxadinal");
            parametros.put("taxaFinal", taxaFretefinal);
        }

        var typedQuery = this.entityManager.createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach(typedQuery::setParameter);

        return typedQuery.getResultList();
    }
}

