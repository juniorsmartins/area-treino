package com.desafiov2picpayjava.adapters.in.mappers;

import com.desafiov2picpayjava.adapters.in.dtos.CarteiraDtoOut;
import com.desafiov2picpayjava.application.core.domain.Carteira;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarteiraDtoOutMapper {

    CarteiraDtoOut toCarteiraDtoOut(Carteira carteira);
}

