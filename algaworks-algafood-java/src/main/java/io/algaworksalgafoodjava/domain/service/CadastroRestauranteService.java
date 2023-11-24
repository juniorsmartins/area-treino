package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.exception.EntidadeEmUsoException;
import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import io.algaworksalgafoodjava.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    public Restaurante atualizar(Restaurante restaurante) {

        var idCozinha = restaurante.getCozinha().getId();
        var cozinha = this.cozinhaRepository.buscar(idCozinha);
        if (ObjectUtils.isEmpty(cozinha)) {
            throw new IllegalArgumentException(String.format("Não existe cozinha com id %s", idCozinha));
        }

        var restauranteDoBanco = this.restauranteRepository.buscar(restaurante.getId());
        if (ObjectUtils.isEmpty(restauranteDoBanco)) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe restaurante com id %s", restaurante.getId()));
        }
        restauranteDoBanco.setCozinha(cozinha);

        BeanUtils.copyProperties(restaurante, restauranteDoBanco, "id", "cozinha");
        return this.restauranteRepository.salvar(restauranteDoBanco);
    }

    public void excluir(final Long id) {

        try {
            this.restauranteRepository.remover(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com id %s não encontrado.", id));

        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String
                .format("Restaurante com id %s não pode ser removido, pois está em uso.", id));
        }
    }
}

