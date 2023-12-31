package udemy.hexagonal.adapters.in.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import udemy.hexagonal.adapters.in.controller.request.CustomerRequest;
import udemy.hexagonal.adapters.in.controller.response.CustomerResponse;
import udemy.hexagonal.application.core.domain.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "isValidCpf", ignore = true)
    Customer toCustomer(CustomerRequest customerRequest);

    @Mapping(target = "zipCode", ignore = true)
    CustomerResponse toCustomerResponse(Customer customer);
}

