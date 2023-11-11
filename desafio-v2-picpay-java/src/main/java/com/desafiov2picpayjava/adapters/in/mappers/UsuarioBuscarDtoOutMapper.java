package com.desafiov2picpayjava.adapters.in.mappers;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioBuscarDtoOut;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioBuscarDtoOutMapper {

    UsuarioBuscarDtoOut toUsuarioBuscarDtoOut(Usuario usuario);
}

