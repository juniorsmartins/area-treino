package io.algaworksalgafoodjava.domain.repository;

import io.algaworksalgafoodjava.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQueries {

    List<Restaurante> consultaDinamicaComCriteria(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFretefinal);

    List<Restaurante> consultaDinamicaComJpql(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

    List<Restaurante> consultaComFreteGratis(String nome);
}

