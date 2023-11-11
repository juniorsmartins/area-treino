package com.desafiov2picpayjava.adapters.in.mappers;

import com.desafiov2picpayjava.adapters.in.dtos.CarteiraDepositarDtoIn;
import com.desafiov2picpayjava.application.core.domain.Carteira;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarteiraDepositarDtoInMapper {

    Carteira toCarteira(CarteiraDepositarDtoIn carteiraDepositarDtoIn);
}

