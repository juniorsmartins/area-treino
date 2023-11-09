package com.desafiov2picpayjava.adapters.out.mappers;

import com.desafiov2picpayjava.adapters.out.entities.UsuarioOrm;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioOrmMapper {

    UsuarioOrm toUsuarioOrm(Usuario usuario);

    Usuario toUsuario(UsuarioOrm usuarioOrm);
}

