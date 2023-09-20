package io.udemyapirestjava.adapters.in.controllers;

import io.udemyapirestjava.adapters.in.controllers.mapper.PersonResponseMapper;
import io.udemyapirestjava.adapters.in.controllers.response.PersonResponse;
import io.udemyapirestjava.application.ports.in.PersonFindByIdInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    @Autowired
    private PersonFindByIdInputPort personFindByIdInputPort;

    @Autowired
    private PersonResponseMapper personResponseMapper;

    @GetMapping(path = "/{id}")
    public PersonResponse findyById(@PathVariable(name = "id") final Long id) {

        return Optional.of(id)
                .map(this.personFindByIdInputPort::find)
                .map(this.personResponseMapper::toPersonResponse)
                .orElseThrow();
    }
}

