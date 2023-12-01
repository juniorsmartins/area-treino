package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.exception.EntidadeEmUsoException;
import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Cozinha;
import io.algaworksalgafoodjava.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public List<Cozinha> listar() {
        return this.cozinhaRepository.findAll();
    }

    public Cozinha buscar(final Long id) {
        return this.cozinhaRepository.findById(id)
                .orElseThrow();
    }

    public Cozinha salvar(Cozinha cozinha) {
        return this.cozinhaRepository.save(cozinha);
    }

    public void excluir(final Long id) {

        try {
            this.cozinhaRepository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha com id %s não encontrada.", id));

        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String
                .format("Cozinha com id %s não pode ser removida, pois está em uso.", id));
        }
    }
}

