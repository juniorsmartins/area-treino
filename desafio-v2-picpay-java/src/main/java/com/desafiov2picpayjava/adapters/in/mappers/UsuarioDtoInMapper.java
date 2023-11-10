package com.desafiov2picpayjava.adapters.in.mappers;

import com.desafiov2picpayjava.adapters.in.dtos.UsuarioCadastrarDtoIn;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.core.domain.enums.TipoUsuarioEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UsuarioDtoInMapper {

    @Mapping(target = "tipo", source = "tipo", qualifiedByName = "tipoStringToEnum")
    Usuario toUsuario(UsuarioCadastrarDtoIn usuarioCadastrarDtoIn);

    @Named("tipoStringToEnum")
    default TipoUsuarioEnum tipoStringToEnum(String tipo) {
        for (TipoUsuarioEnum enumValue : TipoUsuarioEnum.values()) {
            if (enumValue.getTipo().equalsIgnoreCase(tipo)) {
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Tipo de Usuário desconhecido: " + tipo);
    }
}

