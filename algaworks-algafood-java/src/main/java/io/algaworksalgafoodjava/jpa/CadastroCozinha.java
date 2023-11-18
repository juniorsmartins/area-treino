package io.algaworksalgafoodjava.jpa;

import io.algaworksalgafoodjava.domain.model.Cozinha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listar() {

        return this.entityManager.createQuery("from Cozinha", Cozinha.class)
            .getResultList();
    }
}

