package com.desafiov2picpayjava.config.exceptions.http_409;

import java.io.Serial;

public abstract class RegrasDeNegocioException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 1L;

  public RegrasDeNegocioException(String mensagem) {
    super(mensagem);
  }
}

