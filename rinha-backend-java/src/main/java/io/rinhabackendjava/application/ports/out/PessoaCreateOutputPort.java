package io.rinhabackendjava.application.ports.out;

import io.rinhabackendjava.application.core.domain.PessoaBusiness;

public interface PessoaCreateOutputPort {

    PessoaBusiness create(PessoaBusiness pessoaBusiness);
}

