package udemy.hexagonal.application.ports.out;

import udemy.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerOutputPort {

    void update(Customer customer);
}

