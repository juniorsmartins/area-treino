package udemy.hexagonal.application.ports.out;

import udemy.hexagonal.application.core.domain.Address;

public interface FindAddressByZipCodeOutputPort {

    Address find(String zipCode);
}

