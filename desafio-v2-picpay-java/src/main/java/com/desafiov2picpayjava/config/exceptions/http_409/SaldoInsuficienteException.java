package com.desafiov2picpayjava.config.exceptions.http_409;

import java.io.Serial;

public final class SaldoInsuficienteException extends RegrasDeNegocioException {

  @Serial
  private static final long serialVersionUID = 1L;

  public SaldoInsuficienteException() {
    super("Saldo insuficiente!");
  }
}

