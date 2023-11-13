package com.desafiov2picpayjava.config.exceptions.http_409;

import java.io.Serial;

public final class LojistaNaoTransfereException extends RegrasDeNegocioException {

  @Serial
  private static final long serialVersionUID = 1L;

  public LojistaNaoTransfereException() {
    super("Lojistas não podem transferir. E a Carteira pagadora é de Lojista.");
  }
}

