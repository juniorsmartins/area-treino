package com.desafiov2picpayjava.config.exceptions.dtos;

import com.desafiov2picpayjava.config.exceptions.enums.TipoDeErroEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ErrorMessage {

    private String titulo; // Um resumo curto e legível do problema (RFC 7807)

    private String tipo; // Uma referência de URI para identificar o tipo de problema. Fornece documentação legível (RFC 7807)

    private Integer status; // O código de status HTTP gerado pelo servidor para esta ocorrência do problema (RFC 7807)

    private String statusText;

    private String detalhe; // Uma explicação legível e específica capaz de ajudar a resolver o problema (RFC 7807)

    private String path;

    private String method;

    private OffsetDateTime dataHoraErro; // Mostra o momento do erro (fora do padrão)

    private Map<String, String> errors;

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, TipoDeErroEnum tipo) {
        this.titulo = tipo.getTitulo();
        this.tipo = tipo.getCaminho();
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.detalhe = message;
        this.dataHoraErro = OffsetDateTime.now();
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result, TipoDeErroEnum tipo) {
        this.titulo = tipo.getTitulo();
        this.tipo = tipo.getCaminho();
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.detalhe = message;
        this.dataHoraErro = OffsetDateTime.now();
        this.addErrors(result);
    }

    private void addErrors(BindingResult result) {
        this.errors = new HashMap<>();
        for (FieldError fieldError: result.getFieldErrors()) {
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}

