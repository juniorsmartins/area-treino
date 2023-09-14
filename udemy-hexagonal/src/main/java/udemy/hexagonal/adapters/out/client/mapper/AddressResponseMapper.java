package udemy.hexagonal.adapters.out.client.mapper;

import org.mapstruct.Mapper;
import udemy.hexagonal.adapters.out.client.response.AddressResponse;
import udemy.hexagonal.application.core.domain.Address;

@Mapper(componentModel = "spring")
public interface AddressResponseMapper {

    Address toAddress(AddressResponse addressResponse);
}

