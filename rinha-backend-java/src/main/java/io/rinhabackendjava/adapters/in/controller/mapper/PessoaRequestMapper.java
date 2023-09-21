package io.rinhabackendjava.adapters.in.controller.mapper;

import io.rinhabackendjava.adapters.in.controller.request.PessoaRequest;
import io.rinhabackendjava.application.core.domain.PessoaBusiness;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaRequestMapper {

    PessoaBusiness toPessoaBusiness(PessoaRequest pessoaRequest);
}

