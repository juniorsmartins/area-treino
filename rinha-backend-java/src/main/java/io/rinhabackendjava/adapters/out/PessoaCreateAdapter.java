package io.rinhabackendjava.adapters.out;

import io.rinhabackendjava.adapters.out.repository.PessoaRepository;
import io.rinhabackendjava.adapters.out.repository.mapper.PessoaEntityMapper;
import io.rinhabackendjava.application.core.domain.PessoaBusiness;
import io.rinhabackendjava.application.ports.out.PessoaCreateOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PessoaCreateAdapter implements PessoaCreateOutputPort {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaEntityMapper pessoaEntityMapper;

    @Override
    public PessoaBusiness create(PessoaBusiness pessoaBusiness) {

        return Optional.of(pessoaBusiness)
                .map(this.pessoaEntityMapper::toPessoaEntity)
                .map(this.pessoaRepository::save)
                .map(this.pessoaEntityMapper::toPessoaBusiness)
                .orElseThrow();
    }
}

