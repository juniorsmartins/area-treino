package com.desafiopicpayjava.infra;

import com.desafiopicpayjava.dtos.ExceptionDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDto> threatDuplicateEntry(DataIntegrityViolationException ex) {

        var exceptionDto = new ExceptionDto("Usuário já cadastrado", "400");

        return ResponseEntity
            .badRequest()
            .body(exceptionDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound(EntityNotFoundException ex) {

        return ResponseEntity
            .notFound()
            .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> exception(Exception ex) {

        var exceptionDto = new ExceptionDto(ex.getMessage(), "500");

        return ResponseEntity
            .internalServerError()
            .body(exceptionDto);
    }
}

