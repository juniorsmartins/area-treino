package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.*;
import com.desafiov2picpayjava.adapters.in.mappers.*;
import com.desafiov2picpayjava.application.ports.in.CarteiraBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.in.CarteiraCadastrarInputPort;
import com.desafiov2picpayjava.application.ports.in.CarteiraDepositarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/carteiras")
@RequiredArgsConstructor
public class CarteiraController {

    private final Logger logger = Logger.getLogger(CarteiraController.class.getName());

    private final CarteiraCadastrarInputPort carteiraCadastrarInputPort;

    private final CarteiraBuscarPorIdInputPort carteiraBuscarPorIdInputPort;

    private final CarteiraDepositarInputPort carteiraDepositarInputPort;

    private final CarteiraCadastrarDtoInMapper carteiraCadastrarDtoInMapper;

    private final CarteiraCadastrarDtoOutMapper carteiraCadastrarDtoOutMapper;

    private final CarteiraBuscarDtoOutMapper carteiraBuscarDtoOutMapper;

    private final CarteiraDepositarDtoInMapper carteiraDepositarDtoInMapper;

    private final CarteiraDepositarDtoOutMapper carteiraDepositarDtoOutMapper;

    @PostMapping
    public ResponseEntity<CarteiraCadastrarDtoOut> cadastrar(@RequestBody @Valid CarteiraCadastrarDtoIn dtoIn) {

        this.logger.info("Controller - recebida requisição para cadastrar Carteira.");

        var carteira = Optional.of(dtoIn)
            .map(this.carteiraCadastrarDtoInMapper::toCarteira)
            .map(this.carteiraCadastrarInputPort::cadastrar)
            .map(this.carteiraCadastrarDtoOutMapper::toCarteiraCadastrarDtoOut)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Controller - concluído com sucesso cadastro de Carteira.");

        return ResponseEntity
            .created(URI.create("/api/v1/carteiras/" + carteira.id()))
            .body(carteira);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CarteiraBuscarDtoOut> buscarPorId(@PathVariable(name = "id") final Long id) {

        this.logger.info("Controller - recebida requisição para buscar Carteira por id.");

        var dtoOut = Optional.of(id)
            .map(this.carteiraBuscarPorIdInputPort::buscarPorId)
            .map(this.carteiraBuscarDtoOutMapper::toCarteiraBuscarDtoOut)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Controller - concluído com sucesso busca de Carteira por id.");

        return ResponseEntity
            .ok()
            .body(dtoOut);
    }

    @PutMapping
    public ResponseEntity<CarteiraDepositarDtoOut> depositar(@RequestBody @Valid CarteiraDepositarDtoIn dtoIn) {

        this.logger.info("Controller - recebida requisição para depositar valor na Carteira.");

        var dtoOut = Optional.of(dtoIn)
            .map(this.carteiraDepositarDtoInMapper::toCarteira)
            .map(this.carteiraDepositarInputPort::depositar)
            .map(this.carteiraDepositarDtoOutMapper::toCarteiraDepositarDtoOut)
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Controller - concluído com sucesso depósito de valor na Carteira.");

        return ResponseEntity
            .ok()
            .body(dtoOut);
    }
}

