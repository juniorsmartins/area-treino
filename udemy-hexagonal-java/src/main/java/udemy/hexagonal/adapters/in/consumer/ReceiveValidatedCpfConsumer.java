package udemy.hexagonal.adapters.in.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import udemy.hexagonal.adapters.in.consumer.mapper.CustomerMessageMapper;
import udemy.hexagonal.adapters.in.consumer.message.CustomerMessage;
import udemy.hexagonal.application.ports.in.UpdateCustomerInputPort;

@Component
public class ReceiveValidatedCpfConsumer {

    @Autowired
    private UpdateCustomerInputPort updateCustomerInputPort;

    @Autowired
    private CustomerMessageMapper customerMessageMapper;

    @KafkaListener(topics = "topico-cpf-validated", groupId = "udemy")
    public void receive(CustomerMessage customerMessage) {

        var customer = this.customerMessageMapper.toCustomer(customerMessage);
        this.updateCustomerInputPort.update(customer, customerMessage.getZipCode());
    }
}

