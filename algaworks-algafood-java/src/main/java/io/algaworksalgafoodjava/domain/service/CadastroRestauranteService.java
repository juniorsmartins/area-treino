package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;

    public List<Restaurante> listar() {
        return this.restauranteRepository.listar();
    }

    public Restaurante buscar(final Long id) {
        return this.restauranteRepository.buscar(id);
    }
}

