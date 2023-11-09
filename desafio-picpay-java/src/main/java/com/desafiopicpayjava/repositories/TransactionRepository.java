package com.desafiopicpayjava.repositories;

import com.desafiopicpayjava.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> { }

