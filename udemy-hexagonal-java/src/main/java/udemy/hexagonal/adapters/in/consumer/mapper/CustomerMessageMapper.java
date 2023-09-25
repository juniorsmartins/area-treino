package udemy.hexagonal.adapters.in.consumer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import udemy.hexagonal.adapters.in.consumer.message.CustomerMessage;
import udemy.hexagonal.application.core.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMessageMapper {

    @Mapping(target = "address", ignore = true)
    Customer toCustomer(CustomerMessage customerMessage);
}

