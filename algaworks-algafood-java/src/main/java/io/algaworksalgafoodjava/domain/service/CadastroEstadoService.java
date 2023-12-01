package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.exception.EntidadeEmUsoException;
import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Estado;
import io.algaworksalgafoodjava.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CadastroEstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return this.estadoRepository.findAll();
    }

    public Optional<Estado> buscar(final Long id) {
        return this.estadoRepository.findById(id);
    }

    public Estado salvar(Estado estado) {
        return this.estadoRepository.save(estado);
    }

    public void excluir(final Long id) {

        try {
            this.estadoRepository.deleteById(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Estado com id %s não encontrado.", id));

        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String
                .format("Estado com id %s não pode ser removido, pois está em uso.", id));
        }
    }
}

