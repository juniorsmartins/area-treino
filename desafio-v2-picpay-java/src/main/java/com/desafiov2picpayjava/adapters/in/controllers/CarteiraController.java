package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.*;
import com.desafiov2picpayjava.adapters.in.mappers.CarteiraBuscarDtoOutMapper;
import com.desafiov2picpayjava.adapters.in.mappers.CarteiraCadastrarDtoInMapper;
import com.desafiov2picpayjava.adapters.in.mappers.CarteiraCadastrarDtoOutMapper;
import com.desafiov2picpayjava.application.ports.in.CarteiraBuscarPorIdInputPort;
import com.desafiov2picpayjava.application.ports.in.CarteiraCadastrarInputPort;
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

    private final CarteiraCadastrarDtoInMapper carteiraCadastrarDtoInMapper;

    private final CarteiraCadastrarDtoOutMapper carteiraCadastrarDtoOutMapper;

    private final CarteiraBuscarDtoOutMapper carteiraBuscarDtoOutMapper;

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

    @PutMapping(path = "/{id}")
    public ResponseEntity<CarteiraDepositarDtoOut> depositar(@PathVariable(name = "id") final Long id,
                                                             @RequestBody @Valid CarteiraDepositarDtoIn dtoIn) {

        this.logger.info("Controller - recebida requisição para depositar valor na Carteira.");

        this.logger.info("Controller - concluído com sucesso depósito de valor na Carteira.");

        return ResponseEntity
            .ok()
            .body(null);
    }
}

