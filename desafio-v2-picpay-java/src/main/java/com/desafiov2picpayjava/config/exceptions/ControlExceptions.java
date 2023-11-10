package com.desafiov2picpayjava.config.exceptions;

import com.desafiov2picpayjava.config.exceptions.dtos.ErrorMessage;
import com.desafiov2picpayjava.config.exceptions.enums.TipoDeErroEnum;
import com.desafiov2picpayjava.config.exceptions.http_400.RequisicaoMalFormuladaException;
import com.desafiov2picpayjava.config.exceptions.http_404.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

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

    @ExceptionHandler(RequisicaoMalFormuladaException.class)
    public ResponseEntity<ErrorMessage> erroNaRequisicao(RequisicaoMalFormuladaException ex,
                                                          HttpServletRequest request) {
        logger.info("Exception de requisição mal formulada: " + ex);

        var mensagem = new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage(), TipoDeErroEnum.DADOS_INVALIDOS);

        return ResponseEntity
            .badRequest()
            .body(mensagem);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> recursoNaoEncontrado(RecursoNaoEncontradoException ex,
                                                             HttpServletRequest request) {
        logger.info("Exception de recurso não encontrado: " + ex);

        var mensagem = new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage(), TipoDeErroEnum.RECURSO_NAO_ENCONTRADO);

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(mensagem);
    }
}

