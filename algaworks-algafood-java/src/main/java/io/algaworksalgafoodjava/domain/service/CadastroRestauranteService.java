package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import io.algaworksalgafoodjava.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return this.restauranteRepository.listar();
    }

    public Restaurante buscar(final Long id) {
        return this.restauranteRepository.buscar(id);
    }

    public Restaurante salvar(Restaurante restaurante) {

        var idCozinha = restaurante.getCozinha().getId();
        var cozinha = this.cozinhaRepository.buscar(idCozinha);

        if (ObjectUtils.isEmpty(cozinha)) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cozinha com id %s", idCozinha));
        }

        restaurante.setCozinha(cozinha);
        return this.restauranteRepository.salvar(restaurante);
    }
}

