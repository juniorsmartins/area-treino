package io.apirest.estacionamento.java.web.exception;

import io.apirest.estacionamento.java.web.exception.ex.CpfUniqueViolationException;
import io.apirest.estacionamento.java.web.exception.ex.EntidadeNotFoundException;
import io.apirest.estacionamento.java.web.exception.ex.PasswordInvalidException;
import io.apirest.estacionamento.java.web.exception.ex.UsernameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.security.access.*;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    // Tratamento de erros de validação (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpServletRequest request,
                                                               BindingResult result) {
        log.error("Api Error - ", ex);

        var response =
                new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválido(s)", result);

        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(value = {UsernameUniqueViolationException.class, CpfUniqueViolationException.class})
    public ResponseEntity<ErrorMessage> uniqueViolation(RuntimeException ex,
                                                        HttpServletRequest request) {
        log.error("Api Error - ", ex);

        var response = new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(EntidadeNotFoundException.class)
    public ResponseEntity<ErrorMessage> entidadeNotFound(RuntimeException ex, HttpServletRequest request) {

        log.error("Api Error - ", ex);

        var response = new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> passwordInvalid(RuntimeException ex, HttpServletRequest request) {

        log.error("Api Error - ", ex);

        var response = new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> accessDenied(AccessDeniedException ex, HttpServletRequest request) {

        log.error("Api Error - ", ex);

        var response = new ErrorMessage(request, HttpStatus.FORBIDDEN, ex.getMessage());

        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
}

