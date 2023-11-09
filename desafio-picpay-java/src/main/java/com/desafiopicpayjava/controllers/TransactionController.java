package com.desafiopicpayjava.controllers;

import com.desafiopicpayjava.domain.transaction.Transaction;
import com.desafiopicpayjava.dtos.TransactionDto;
import com.desafiopicpayjava.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDto transactionDto) throws Exception {

        var newTransaction = this.transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}

