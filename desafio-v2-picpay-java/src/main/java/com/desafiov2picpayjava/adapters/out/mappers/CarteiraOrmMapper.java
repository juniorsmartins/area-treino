package com.desafiov2picpayjava.adapters.out.mappers;

import com.desafiov2picpayjava.adapters.out.entities.CarteiraOrm;
import com.desafiov2picpayjava.application.core.domain.Carteira;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarteiraOrmMapper {

    CarteiraOrm toCarteiraOrm(Carteira carteira);

    Carteira toCarteira(CarteiraOrm carteiraOrm);
}

