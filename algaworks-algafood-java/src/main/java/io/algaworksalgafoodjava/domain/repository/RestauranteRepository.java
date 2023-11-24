package io.algaworksalgafoodjava.domain.repository;

import io.algaworksalgafoodjava.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

    List<Restaurante> listar();

    Restaurante buscar(Long id);

    Restaurante salvar(Restaurante restaurante);

    void remover(Long id);
}

