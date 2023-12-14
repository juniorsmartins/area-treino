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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final CozinhaRepository cozinhaRepository;

    public List<Restaurante> listar() {
        return this.restauranteRepository.findAll();
    }

    public Optional<Restaurante> buscar(final Long id) {
        return this.restauranteRepository.findById(id);
    }

    public Restaurante salvar(Restaurante restaurante) {

        var idCozinha = restaurante.getCozinha().getId();

        return this.cozinhaRepository.findById(idCozinha)
            .map(cozinhaEncontrada -> {
                restaurante.setCozinha(cozinhaEncontrada);
                return this.restauranteRepository.save(restaurante);
            })
            .orElseThrow(() -> new EntidadeNaoEncontradaException(String
                .format("Não existe cozinha com id %s", idCozinha)));
    }

    public Restaurante atualizar(Restaurante restaurante) {

        var idRestaurante = restaurante.getId();
        var restauranteEncontrado = this.restauranteRepository.findById(idRestaurante)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(String
                .format("Não existe restaurante com id %s", restaurante.getId())));

        var idCozinha = restaurante.getCozinha().getId();
        var cozinhaEncontrada = this.cozinhaRepository.findById(idCozinha)
            .orElseThrow(() -> new IllegalArgumentException(String
                .format("Não existe cozinha com id %s", idCozinha)));

        restauranteEncontrado.setCozinha(cozinhaEncontrada);
        BeanUtils.copyProperties(restaurante, restauranteEncontrado, "id", "cozinha", "formasPagamento");

        return this.restauranteRepository.save(restauranteEncontrado);
    }

    public Restaurante atualizarParcial(final Long id, Map<String, Object> campos) {

        var restauranteEncontrado = this.restauranteRepository.findById(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(String
                .format("Não existe restaurante com id %s", id)));

        var objectMapper = new ObjectMapper();
        var dadosPraAtualizar = objectMapper.convertValue(campos, Restaurante.class);

        Cozinha cozinha = null;
        if (!ObjectUtils.isEmpty(dadosPraAtualizar.getCozinha().getId())) {

            var idCozinha = dadosPraAtualizar.getCozinha().getId();
            cozinha = this.cozinhaRepository.findById(idCozinha)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Não existe cozinha com id %s", idCozinha)));
        }
        dadosPraAtualizar.setCozinha(cozinha);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true); // Autoriza acesso a atributos privados - quebra o encapsulamento
            Object novoValor = ReflectionUtils.getField(field, dadosPraAtualizar); // Buscar o valor do campo/field
            ReflectionUtils.setField(field, restauranteEncontrado, novoValor);
        });

        return this.restauranteRepository.save(restauranteEncontrado);
    }

    public void excluir(final Long id) {

        try {
            this.restauranteRepository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante com id %s não encontrado.", id));

        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String
                .format("Restaurante com id %s não pode ser removido, pois está em uso.", id));
        }
    }
}

