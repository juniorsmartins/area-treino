package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> listar() {
        return this.entityManager.createQuery("from Restaurante", Restaurante.class)
            .getResultList();
    }

    @Override
    public Restaurante buscar(final Long id) {
        return this.entityManager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante) {
        return this.entityManager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(final Long id) {
        var restaurante = this.buscar(id);

        if (ObjectUtils.isEmpty(restaurante)) {
            throw new EmptyResultDataAccessException(1);
        }

        this.entityManager.remove(restaurante);
    }
}

