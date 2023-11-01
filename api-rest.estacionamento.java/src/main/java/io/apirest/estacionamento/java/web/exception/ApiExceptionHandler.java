package io.apirest.estacionamento.java.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    // Tratamento de erros de validação (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpServletRequest request,
                                                               BindingResult result) {

        log.error("Api Error - ", ex);

//         var response = new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), result); // Pode ser assim ou abaixo
        var response =
                new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválido(s)", result);

        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .body(response);
    }
}

