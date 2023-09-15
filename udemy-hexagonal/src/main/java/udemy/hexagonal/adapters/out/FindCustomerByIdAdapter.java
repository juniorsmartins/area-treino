package udemy.hexagonal.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import udemy.hexagonal.adapters.out.repository.CustomerRepository;
import udemy.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import udemy.hexagonal.application.core.domain.Customer;
import udemy.hexagonal.application.ports.out.FindCustomerByIdOutputPort;

import java.util.Optional;

@Component
public class FindCustomerByIdAdapter implements FindCustomerByIdOutputPort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public Optional<Customer> find(String id) {

        return this.customerRepository.findById(id)
                .map(this.customerEntityMapper::toCustomer);
    }
}

