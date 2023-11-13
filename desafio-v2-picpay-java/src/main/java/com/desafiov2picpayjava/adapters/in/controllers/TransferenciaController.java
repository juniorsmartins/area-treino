package com.desafiov2picpayjava.adapters.in.controllers;

import com.desafiov2picpayjava.adapters.in.dtos.TransferenciaBuscarDtoOut;
import com.desafiov2picpayjava.adapters.in.dtos.TransferenciaDtoIn;
import com.desafiov2picpayjava.adapters.in.mappers.TransferenciaBuscarDtoOutMapper;
import com.desafiov2picpayjava.adapters.in.mappers.TransferenciaDtoInMapper;
import com.desafiov2picpayjava.application.ports.in.TransferenciaBuscarTodosInputPort;
import com.desafiov2picpayjava.application.ports.in.TransferenciaEfetuarInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/transferencias")
@RequiredArgsConstructor
public class TransferenciaController {

    private final Logger logger = Logger.getLogger(TransferenciaController.class.getName());

    private final TransferenciaEfetuarInputPort transferenciaEfetuarInputPort;

    private final TransferenciaBuscarTodosInputPort transferenciaBuscarTodosInputPort;

    private final TransferenciaDtoInMapper transferenciaDtoInMapper;

    private final TransferenciaBuscarDtoOutMapper transferenciaBuscarDtoOutMapper;

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

    @GetMapping
    public ResponseEntity<List<TransferenciaBuscarDtoOut>> buscarTodos() {

        this.logger.info("Controller - recebida requisição para buscar o histórico de Transferências.");

        var transferencias = this.transferenciaBuscarTodosInputPort.buscarTodos()
            .stream()
            .map(this.transferenciaBuscarDtoOutMapper::toTransferenciaBuscarDtoOut)
            .toList();

        this.logger.info("Controller - concluído com sucesso requisição para buscar o histórico de Transferências.");

        return ResponseEntity
            .ok()
            .body(transferencias);
    }
}

