package com.desafiov2picpayjava.config.exceptions;

import com.desafiov2picpayjava.config.exceptions.dtos.ErrorMessage;
import com.desafiov2picpayjava.config.exceptions.enums.TipoDeErroEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Logger;

@RestControllerAdvice
public class ControlExceptions {

    private final Logger logger = Logger.getLogger(ControlExceptions.class.getName());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> errosDeBeanValidation(MethodArgumentNotValidException ex,
                                                              HttpServletRequest request,
                                                              BindingResult bindingResult) {
        logger.info("Exception Bean Validation: " + ex);

        var mensagem = new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage(), bindingResult, TipoDeErroEnum.DADOS_INVALIDOS);

        return ResponseEntity
            .badRequest()
            .body(mensagem);
    }
}

