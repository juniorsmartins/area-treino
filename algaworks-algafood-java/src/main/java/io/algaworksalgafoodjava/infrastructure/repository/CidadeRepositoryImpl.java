package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.Cidade;
import io.algaworksalgafoodjava.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return this.entityManager.createQuery("from Cidade", Cidade.class)
            .getResultList();
    }

    @Override
    public Cidade buscar(final Long id) {
        return this.entityManager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade) {
        return this.entityManager.merge(cidade);
    }

    @Transactional
    @Override
    public void remover(final Long id) {
        var cidade = this.buscar(id);

        if (ObjectUtils.isEmpty(cidade)) {
            throw new EmptyResultDataAccessException(1);
        }

        this.entityManager.remove(cidade);
    }
}
