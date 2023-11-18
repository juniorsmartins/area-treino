package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.Permissao;
import io.algaworksalgafoodjava.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> listar() {
        return this.entityManager.createQuery("from Permissao", Permissao.class)
            .getResultList();
    }

    @Override
    public Permissao buscar(final Long id) {
        return this.entityManager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao salvar(Permissao permissao) {
        return this.entityManager.merge(permissao);
    }

    @Transactional
    @Override
    public void remover(Permissao permissao) {
        permissao = this.buscar(permissao.getId());
        this.entityManager.remove(permissao);
    }
}

