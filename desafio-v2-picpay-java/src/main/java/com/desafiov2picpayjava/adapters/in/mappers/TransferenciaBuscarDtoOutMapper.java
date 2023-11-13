package com.desafiov2picpayjava.adapters.in.mappers;

import com.desafiov2picpayjava.adapters.in.dtos.TransferenciaBuscarDtoOut;
import com.desafiov2picpayjava.application.core.domain.Transferencia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferenciaBuscarDtoOutMapper {

    TransferenciaBuscarDtoOut toTransferenciaBuscarDtoOut(Transferencia transferencia);
}

