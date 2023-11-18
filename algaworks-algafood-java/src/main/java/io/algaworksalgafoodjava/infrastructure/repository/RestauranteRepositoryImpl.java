package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.repository.RestauranteRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public void remover(Restaurante restaurante) {
        restaurante = this.buscar(restaurante.getId());
        this.entityManager.remove(restaurante);
    }
}

