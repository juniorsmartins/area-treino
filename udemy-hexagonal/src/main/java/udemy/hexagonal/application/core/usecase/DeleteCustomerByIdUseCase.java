package udemy.hexagonal.application.core.usecase;

import udemy.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import udemy.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import udemy.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;

public class DeleteCustomerByIdUseCase implements DeleteCustomerByIdInputPort {

    private final FindCustomerByIdInputPort findCustomerByIdInputPort;

    private final DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort;

    public DeleteCustomerByIdUseCase(FindCustomerByIdInputPort findCustomerByIdInputPort,
                                     DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort) {
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.deleteCustomerByIdOutputPort = deleteCustomerByIdOutputPort;
    }

    @Override
    public void delete(final String id) {

        this.findCustomerByIdInputPort.find(id);
        this.deleteCustomerByIdOutputPort.delete(id);
    }
}

