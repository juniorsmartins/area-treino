package udemy.hexagonal.adapters.in.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import udemy.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import udemy.hexagonal.adapters.in.controller.request.CustomerRequest;
import udemy.hexagonal.application.ports.in.InsertCustomerInputPort;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    @Autowired
    private InsertCustomerInputPort insertCustomerInputPort;

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid CustomerRequest customerRequest) {
        var customer = this.customerMapper.toCustomer(customerRequest);
        this.insertCustomerInputPort.insert(customer, customerRequest.getZipCode());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}

