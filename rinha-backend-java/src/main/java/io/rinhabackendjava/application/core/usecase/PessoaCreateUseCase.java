package io.rinhabackendjava.application.core.usecase;

import io.rinhabackendjava.application.core.domain.PessoaBusiness;
import io.rinhabackendjava.application.ports.in.PessoaCreateInputPort;
import io.rinhabackendjava.application.ports.out.PessoaCreateOutputPort;

import java.util.Optional;

public class PessoaCreateUseCase implements PessoaCreateInputPort {

    private final PessoaCreateOutputPort pessoaCreateOutputPort;

    public PessoaCreateUseCase(PessoaCreateOutputPort pessoaCreateOutputPort) {
        this.pessoaCreateOutputPort = pessoaCreateOutputPort;
    }

    @Override
    public PessoaBusiness create(PessoaBusiness pessoaBusiness) {

        return Optional.of(pessoaBusiness)
                .map(this.pessoaCreateOutputPort::create)
                .orElseThrow();
    }
}

