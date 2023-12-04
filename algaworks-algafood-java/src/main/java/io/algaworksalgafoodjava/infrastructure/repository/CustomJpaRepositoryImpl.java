package io.algaworksalgafoodjava.infrastructure.repository;

import io.algaworksalgafoodjava.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class CustomJpaRepositoryImpl<T, I> extends SimpleJpaRepository<T, I> implements CustomJpaRepository<T, I> {

    private final EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        var tipoDaClasseT = super.getDomainClass();
        var nomeDaClasseT = tipoDaClasseT.getName();

        var jpql = "from " + nomeDaClasseT;

        T entity = this.entityManager.createQuery(jpql, tipoDaClasseT)
            .setMaxResults(1)
            .getSingleResult();

        return Optional.ofNullable(entity);
    }
}

