package udemy.hexagonal.adapters.out.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import udemy.hexagonal.adapters.out.repository.entity.CustomerEntity;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> { }

