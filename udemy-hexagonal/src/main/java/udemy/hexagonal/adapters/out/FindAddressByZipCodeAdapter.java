package udemy.hexagonal.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import udemy.hexagonal.adapters.out.client.FindAddressByZipCodeClient;
import udemy.hexagonal.adapters.out.client.mapper.AddressResponseMapper;
import udemy.hexagonal.application.core.domain.Address;
import udemy.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;

public class FindAddressByZipCodeAdapter implements FindAddressByZipCodeOutputPort {

    @Autowired
    private FindAddressByZipCodeClient findAddressByZipCodeClient;

    @Autowired
    private AddressResponseMapper addressResponseMapper;

    @Override
    public Address find(final String zipCode) {

        var addressResponse = this.findAddressByZipCodeClient.find(zipCode);
        return this.addressResponseMapper.toAddress(addressResponse);
    }
}

