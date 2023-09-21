package io.udemyapirestjava.adapters.in.controllers;

import io.udemyapirestjava.adapters.in.controllers.mapper.PersonRequestMapper;
import io.udemyapirestjava.adapters.in.controllers.mapper.PersonResponseMapper;
import io.udemyapirestjava.adapters.in.controllers.request.PersonRequest;
import io.udemyapirestjava.adapters.in.controllers.response.PersonResponse;
import io.udemyapirestjava.application.ports.in.PersonCreateInputPort;
import io.udemyapirestjava.application.ports.in.PersonFindAllInputPort;
import io.udemyapirestjava.application.ports.in.PersonFindByIdInputPort;
import io.udemyapirestjava.application.ports.in.PersonUpdateInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/persons")
public class PersonController {

    @Autowired
    private PersonFindByIdInputPort personFindByIdInputPort;

    @Autowired
    private PersonFindAllInputPort personFindAllInputPort;

    @Autowired
    private PersonCreateInputPort personCreateInputPort;

    @Autowired
    private PersonUpdateInputPort personUpdateInputPort;

    @Autowired
    private PersonResponseMapper personResponseMapper;

    @Autowired
    private PersonRequestMapper personRequestMapper;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonResponse findyById(@PathVariable(name = "id") final Long id) {

        return Optional.of(id)
                .map(this.personFindByIdInputPort::find)
                .map(this.personResponseMapper::toPersonResponse)
                .orElseThrow();
    }

    @GetMapping
    public List<PersonResponse> find() {

        return this.personFindAllInputPort.find()
                .stream()
                .map(this.personResponseMapper::toPersonResponse)
                .toList();
    }

    @PostMapping
    public PersonResponse create(@RequestBody @Valid PersonRequest personRequest) {

        return Optional.of(personRequest)
                .map(this.personRequestMapper::toPerson)
                .map(this.personCreateInputPort::create)
                .map(this.personResponseMapper::toPersonResponse)
                .orElseThrow();
    }

    @PutMapping(path = "/{id}")
    public PersonResponse update(@RequestBody @Valid PersonRequest personRequest,
                       @PathVariable(name = "id") final Long id) {

        return Optional.of(personRequest)
                .map(this.personRequestMapper::toPerson)
                .map(person -> {
                    person.setId(id);
                    return this.personUpdateInputPort.update(person);
                })
                .map(this.personResponseMapper::toPersonResponse)
                .orElseThrow();
    }
}

