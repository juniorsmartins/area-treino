package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.model.FormaPagamento;
import io.algaworksalgafoodjava.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FormaPagamento> listar() {
        return this.entityManager.createQuery("from FormaPagamento", FormaPagamento.class)
            .getResultList();
    }

    @Override
    public FormaPagamento buscar(final Long id) {
        return this.entityManager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return this.entityManager.merge(formaPagamento);
    }

    @Transactional
    @Override
    public void remover(FormaPagamento formaPagamento) {
        formaPagamento = this.buscar(formaPagamento.getId());
        this.entityManager.remove(formaPagamento);
    }
}

