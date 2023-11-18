package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.Estado;
import io.algaworksalgafoodjava.domain.repository.EstadoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        return this.entityManager.createQuery("from Estado", Estado.class)
            .getResultList();
    }

    @Override
    public Estado buscar(final Long id) {
        return this.entityManager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado) {
        return this.entityManager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Estado estado) {
        estado = this.buscar(estado.getId());
        this.entityManager.remove(estado);
    }
}

