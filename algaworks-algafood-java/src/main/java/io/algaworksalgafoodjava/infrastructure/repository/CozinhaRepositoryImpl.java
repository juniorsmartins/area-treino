package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.Cozinha;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar() {
        return this.entityManager.createQuery("from Cozinha", Cozinha.class)
            .getResultList();
    }

    @Override
    public List<Cozinha> consultarPorNome(String nomeCozinha) {
        return this.entityManager.createQuery("from Cozinha where nome like :nome", Cozinha.class)
            .setParameter("nome", "%" + nomeCozinha + "%")
            .getResultList();
    }

    @Override
    public Cozinha buscar(final Long id) {
        return this.entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return this.entityManager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        var cozinha = this.buscar(id);

        if (ObjectUtils.isEmpty(cozinha)) {
            throw new EmptyResultDataAccessException(1);
        }

        this.entityManager.remove(cozinha);
    }
}
