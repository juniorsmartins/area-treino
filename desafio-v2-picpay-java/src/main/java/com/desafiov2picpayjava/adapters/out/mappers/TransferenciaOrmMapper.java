package com.desafiov2picpayjava.adapters.out.mappers;

import com.desafiov2picpayjava.adapters.out.entities.TransferenciaOrm;
import com.desafiov2picpayjava.application.core.domain.Transferencia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferenciaOrmMapper {

    TransferenciaOrm toTransferenciaOrm(Transferencia transferencia);

    Transferencia toTransferencia(TransferenciaOrm transferenciaOrm);
}

