package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.TransferenciaDtoIn;
import com.desafiov2picpayjava.adapters.in.mappers.TransferenciaDtoInMapper;
import com.desafiov2picpayjava.application.ports.in.TransferenciaEfetuarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/transferencias")
@RequiredArgsConstructor
public class TransferenciaController {

    private final Logger logger = Logger.getLogger(TransferenciaController.class.getName());

    private final TransferenciaEfetuarInputPort transferenciaEfetuarInputPort;

    private final TransferenciaDtoInMapper transferenciaDtoInMapper;

    @PostMapping
    public ResponseEntity<Void> transferir(@RequestBody @Valid TransferenciaDtoIn dtoIn) {

        this.logger.info("Controller - recebida requisição para transferência de valor entre Carteiras.");

        Optional.of(dtoIn)
            .map(this.transferenciaDtoInMapper::toTransferencia)
            .map(transfer -> {
                this.transferenciaEfetuarInputPort.transferir(transfer);
                return true;
            })
            .orElseThrow(NoSuchElementException::new);

        this.logger.info("Controller - concluído com sucesso transferência de valor entre Carteiras.");

        return ResponseEntity
            .noContent()
            .build();
    }
}

