package udemy.hexagonal.adapters.in.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import udemy.hexagonal.adapters.in.controller.request.CustomerRequest;
import udemy.hexagonal.adapters.in.controller.response.CustomerResponse;
import udemy.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import udemy.hexagonal.application.ports.in.InsertCustomerInputPort;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    @Autowired
    private InsertCustomerInputPort insertCustomerInputPort;

    @Autowired
    private FindCustomerByIdInputPort findCustomerByIdInputPort;

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

    @GetMapping(path = "/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable(name = "id") final String id) {

        var customer = this.findCustomerByIdInputPort.find(id);
        var customerResponse = this.customerMapper.toCustomerResponse(customer);

        return ResponseEntity
                .ok()
                .body(customerResponse);
    }
}

