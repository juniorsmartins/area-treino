package io.apirest.estacionamento.java.web.mapper;

import io.apirest.estacionamento.java.entity.Cliente;
import io.apirest.estacionamento.java.web.dto.ClienteCreateDto;
import io.apirest.estacionamento.java.web.dto.ClienteResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {

    public static Cliente toCliente(ClienteCreateDto dto) {
        return new ModelMapper().map(dto, Cliente.class);
    }

    public static ClienteResponseDto toClienteResponseDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}

