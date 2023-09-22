package io.udemyapirestjava.adapters.out.repository;

import io.udemyapirestjava.application.core.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> { }

