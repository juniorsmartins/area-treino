package io.udemyapirestjava.adapters.in.controllers;

import io.udemyapirestjava.adapters.in.controllers.mapper.PersonRequestMapper;
import io.udemyapirestjava.adapters.in.controllers.mapper.PersonResponseMapper;
import io.udemyapirestjava.adapters.in.controllers.request.PersonRequest;
import io.udemyapirestjava.adapters.in.controllers.response.PersonResponse;
import io.udemyapirestjava.application.ports.in.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/persons/v1")
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
    private PersonDeleteInputPort personDeleteInputPort;

    @Autowired
    private PersonResponseMapper personResponseMapper;

    @Autowired
    private PersonRequestMapper personRequestMapper;

    @GetMapping(path = "/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public PersonResponse findyById(@PathVariable(name = "id") final Long id) {

        return Optional.of(id)
                .map(this.personFindByIdInputPort::find)
                .map(this.personResponseMapper::toPersonResponse)
                .map(dtoResponse -> this.gerarHateoasFindById(dtoResponse, id))
                .orElseThrow();
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public List<PersonResponse> find() {

        return this.personFindAllInputPort.find()
                .stream()
                .map(this.personResponseMapper::toPersonResponse)
                .map(dtoResponse -> this.gerarHateoasFindById(dtoResponse, dtoResponse.getKey()))
                .toList();
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" },
                 produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public PersonResponse create(@RequestBody @Valid PersonRequest personRequest) {

        return Optional.of(personRequest)
                .map(this.personRequestMapper::toPerson)
                .map(this.personCreateInputPort::create)
                .map(this.personResponseMapper::toPersonResponse)
                .map(dtoResponse -> this.gerarHateoasFindById(dtoResponse, dtoResponse.getKey()))
                .orElseThrow();
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" },
                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml" })
    public PersonResponse update(@RequestBody @Valid PersonRequest personRequest) {

        return Optional.of(personRequest)
                .map(this.personRequestMapper::toPerson)
                .map(this.personUpdateInputPort::update)
                .map(this.personResponseMapper::toPersonResponse)
                .map(dtoResponse -> this.gerarHateoasFindById(dtoResponse, dtoResponse.getKey()))
                .orElseThrow();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") final Long id) {

        this.personDeleteInputPort.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    private PersonResponse gerarHateoasFindById(PersonResponse personResponse, Long id) {
        personResponse.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(PersonController.class).findyById(id)).withSelfRel());
        return personResponse;
    }
}

