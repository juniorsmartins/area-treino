package udemy.hexagonal.adapters.out.repository.mapper;

import org.mapstruct.Mapper;
import udemy.hexagonal.adapters.out.repository.entity.CustomerEntity;
import udemy.hexagonal.application.core.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity toCustomerEntity(Customer customer);
}

