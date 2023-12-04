package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.infrastructure.repository.RestauranteRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/testes")
@RequiredArgsConstructor
public class TesteController {

    private final RestauranteRepositoryImpl restauranteRepositoryImpl;

    @GetMapping(path = "/jpql")
    public List<Restaurante> consultaDinamicaComJpql(@RequestParam(name = "nome", required = false) String nome,
                                                     @RequestParam(name = "taxaFreteInicial", required = false) BigDecimal taxaFreteInicial,
                                                     @RequestParam(name = "taxaFreteFinal", required = false) BigDecimal taxaFreteFinal) {
        return this.restauranteRepositoryImpl.consultaDinamicaComJpql(nome, taxaFreteInicial, taxaFreteFinal);
    }

    @GetMapping(path = "/criteria")
    public List<Restaurante> consultaDinamicaComCriteria(@RequestParam(name = "nome", required = false) String nome,
                                                         @RequestParam(name = "taxaFreteInicial", required = false) BigDecimal taxaFreteInicial,
                                                         @RequestParam(name = "taxaFreteFinal", required = false) BigDecimal taxaFreteFinal) {
        return this.restauranteRepositoryImpl.consultaDinamicaComCriteria(nome, taxaFreteInicial, taxaFreteFinal);
    }
}

