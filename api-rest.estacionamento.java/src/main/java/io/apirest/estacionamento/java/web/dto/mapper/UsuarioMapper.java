package io.apirest.estacionamento.java.web.dto.mapper;

import io.apirest.estacionamento.java.entity.Usuario;
import io.apirest.estacionamento.java.web.dto.UsuarioCreateDto;
import io.apirest.estacionamento.java.web.dto.UsuarioResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioMapper {

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toUsuarioResponseDto(Usuario usuario) {
        var role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(usuario, UsuarioResponseDto.class);
    }

    public static List<UsuarioResponseDto> toListUsuarioResponseDto(List<Usuario> usuarios) {
        return usuarios.stream()
            .map(UsuarioMapper::toUsuarioResponseDto)
            .toList();
    }
}

