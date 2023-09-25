package udemy.hexagonal.application.ports.in;

import udemy.hexagonal.application.core.domain.Customer;

public interface InsertCustomerInputPort {

    void insert(Customer customer, String zipCode);
}

