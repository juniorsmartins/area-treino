package com.desafiov2picpayjava.application.core.domain.value_objects;

import com.desafiov2picpayjava.config.exceptions.http_400.EmailInvalidoException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.validator.routines.EmailValidator;

@Getter
@Setter
@EqualsAndHashCode(of = {"email"})
public final class CorreioEletronico {

  // Criando um validador de e-mail usando o Apache Commons Validator
  private EmailValidator emailValidator = EmailValidator.getInstance();

  private String email;

  public CorreioEletronico(String email) {
    if (!emailValidator.isValid(email)) {
      throw new EmailInvalidoException(email);
    }
    this.email = email;
  }
}

