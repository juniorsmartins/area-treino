package udemy.hexagonal.application.ports.in;

import udemy.hexagonal.application.core.domain.Customer;

public interface FindCustomerByIdInputPort {

    Customer find(String id);
}

