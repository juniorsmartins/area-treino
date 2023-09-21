package io.rinhabackendjava.application.ports.in;

import io.rinhabackendjava.application.core.domain.PessoaBusiness;

public interface PessoaCreateInputPort {

    PessoaBusiness create(PessoaBusiness pessoaBusiness);
}

