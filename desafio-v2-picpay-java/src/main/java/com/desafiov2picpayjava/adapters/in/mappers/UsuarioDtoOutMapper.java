package com.desafiov2picpayjava.adapters.in.mappers;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioDtoOut;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioDtoOutMapper {

    UsuarioDtoOut toUsuarioDtoOut(Usuario usuario);
}

