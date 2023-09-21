package io.rinhabackendjava.adapters.out.repository.mapper;

import io.rinhabackendjava.adapters.out.repository.entity.PessoaEntity;
import io.rinhabackendjava.application.core.domain.PessoaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaEntityMapper {

    PessoaEntity toPessoaEntity(PessoaBusiness pessoaBusiness);

    PessoaBusiness toPessoaBusiness(PessoaEntity pessoaEntity);
}

