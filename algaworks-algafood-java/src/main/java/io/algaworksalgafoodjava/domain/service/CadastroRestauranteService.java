package io.algaworksalgafoodjava.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.algaworksalgafoodjava.domain.exception.EntidadeEmUsoException;
import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Cozinha;
import io.algaworksalgafoodjava.domain.model.Restaurante;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import io.algaworksalgafoodjava.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

        var restauranteDoBanco = this.restauranteRepository.buscar(restaurante.getId());
        if (ObjectUtils.isEmpty(restauranteDoBanco)) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe restaurante com id %s", restaurante.getId()));
        }

        var idCozinha = restaurante.getCozinha().getId();
        var cozinha = this.cozinhaRepository.buscar(idCozinha);
        if (ObjectUtils.isEmpty(cozinha)) {
            throw new IllegalArgumentException(String.format("Não existe cozinha com id %s", idCozinha));
        }

        restauranteDoBanco.setCozinha(cozinha);
        BeanUtils.copyProperties(restaurante, restauranteDoBanco, "id", "cozinha");
        return this.restauranteRepository.salvar(restauranteDoBanco);
    }

    public Restaurante atualizarParcial(final Long id, Map<String, Object> campos) {

        var restauranteDoBanco = this.restauranteRepository.buscar(id);
        if (ObjectUtils.isEmpty(restauranteDoBanco)) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe restaurante com id %s", id));
        }

        var objectMapper = new ObjectMapper();
        var dadosPraAtualizar = objectMapper.convertValue(campos, Restaurante.class);

        Cozinha cozinha = null;
        if (!ObjectUtils.isEmpty(dadosPraAtualizar.getCozinha().getId())) {
            cozinha = this.cozinhaRepository.buscar(dadosPraAtualizar.getCozinha().getId());
            if (ObjectUtils.isEmpty(cozinha)) {
                throw new IllegalArgumentException(String.format("Não existe cozinha com id %s", dadosPraAtualizar.getCozinha().getId()));
            }
        }
        dadosPraAtualizar.setCozinha(cozinha);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true); // Autoriza acesso a atributos privados - quebra o encapsulamento
            Object novoValor = ReflectionUtils.getField(field, dadosPraAtualizar); // Buscar o valor do campo/field
            ReflectionUtils.setField(field, restauranteDoBanco, novoValor);
        });

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

