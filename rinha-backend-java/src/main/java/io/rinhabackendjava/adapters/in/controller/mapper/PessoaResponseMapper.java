package io.rinhabackendjava.adapters.in.controller.mapper;

import io.rinhabackendjava.adapters.in.controller.response.PessoaResponse;
import io.rinhabackendjava.application.core.domain.PessoaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaResponseMapper {

    PessoaResponse toPessoaResponse(PessoaBusiness pessoaBusiness);
}

