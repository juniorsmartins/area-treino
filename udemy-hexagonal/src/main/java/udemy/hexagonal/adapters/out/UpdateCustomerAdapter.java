package udemy.hexagonal.adapters.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import udemy.hexagonal.adapters.out.repository.CustomerRepository;
import udemy.hexagonal.adapters.out.repository.mapper.CustomerEntityMapper;
import udemy.hexagonal.application.core.domain.Customer;
import udemy.hexagonal.application.ports.out.UpdateCustomerOutputPort;

@Component
public class UpdateCustomerAdapter implements UpdateCustomerOutputPort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public void update(Customer customer) {

        var customerEntity = this.customerEntityMapper.toCustomerEntity(customer);
        this.customerRepository.save(customerEntity);
    }
}

