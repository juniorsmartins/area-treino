package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import io.algaworksalgafoodjava.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final CadastroCozinhaService cadastroCozinhaService;

    public List<Restaurante> listar() {
        return this.restauranteRepository.listar();
    }

    public Restaurante buscar(final Long id) {
        return this.restauranteRepository.buscar(id);
    }

    public Restaurante adicionar(Restaurante restaurante) {

        var cozinha = this.cadastroCozinhaService.buscar(restaurante.getCozinha().getId());
        if (ObjectUtils.isEmpty(cozinha)) {
            throw new EmptyResultDataAccessException(1);
        }

        return this.restauranteRepository.salvar(restaurante);
    }
}

