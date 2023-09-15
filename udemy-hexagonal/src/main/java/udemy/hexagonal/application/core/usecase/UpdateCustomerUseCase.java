package udemy.hexagonal.application.core.usecase;

import udemy.hexagonal.application.core.domain.Customer;
import udemy.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import udemy.hexagonal.application.ports.out.FindAddressByZipCodeOutputPort;
import udemy.hexagonal.application.ports.out.UpdateCustomerOutputPort;

public class UpdateCustomerUseCase {

    private final FindCustomerByIdInputPort findCustomerByIdInputPort;

    private final FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort;

    private final UpdateCustomerOutputPort updateCustomerOutputPort;

    public UpdateCustomerUseCase(FindCustomerByIdInputPort findCustomerByIdInputPort,
                                 FindAddressByZipCodeOutputPort findAddressByZipCodeOutputPort,
                                 UpdateCustomerOutputPort updateCustomerOutputPort) {
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.findAddressByZipCodeOutputPort = findAddressByZipCodeOutputPort;
        this.updateCustomerOutputPort = updateCustomerOutputPort;
    }

    public void update(Customer customer, String zipCode) {

        this.findCustomerByIdInputPort.find(customer.getId());
        var address = this.findAddressByZipCodeOutputPort.find(zipCode);
        customer.setAddress(address);
        this.updateCustomerOutputPort.update(customer);
    }
}

