package io.udemyapirestjava.adapters.out.repository;

import io.udemyapirestjava.adapters.out.repository.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> { }

