package io.udemyapirestjava.config.exception.handler;

import io.udemyapirestjava.config.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // Tratamento das exceptions mais genéricas
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsupportedMathOperationException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlerResourceNotFoundException(ResourceNotFoundException res, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                res.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FailedToSaveException.class)
    public final ResponseEntity<ExceptionResponse> handlerFailedToSaveException(FailedToSaveException fail, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                fail.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FailedToUpdateException.class)
    public final ResponseEntity<ExceptionResponse> handlerFailedToUpdateException(FailedToUpdateException fail, WebRequest webRequest) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                fail.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

