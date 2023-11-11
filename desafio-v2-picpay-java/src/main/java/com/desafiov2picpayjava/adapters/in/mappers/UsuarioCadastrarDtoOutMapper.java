package com.desafiov2picpayjava.adapters.in.mappers;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioCadastrarDtoOut;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioCadastrarDtoOutMapper {

    UsuarioCadastrarDtoOut toUsuarioDtoOut(Usuario usuario);
}

