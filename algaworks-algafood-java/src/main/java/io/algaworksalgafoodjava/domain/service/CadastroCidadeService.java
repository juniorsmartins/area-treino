package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.exception.EntidadeEmUsoException;
import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Cidade;
import io.algaworksalgafoodjava.domain.repository.CidadeRepository;
import io.algaworksalgafoodjava.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;

    private final EstadoRepository estadoRepository;

    public List<Cidade> listar() {
        return this.cidadeRepository.listar();
    }

    public Cidade buscar(final Long id) {
        return this.cidadeRepository.buscar(id);
    }

    public Cidade salvar(Cidade cidade) {
        return this.cidadeRepository.salvar(cidade);
    }

    public Cidade atualizar(Cidade cidade) {

        var idCidade = cidade.getId();
        var cidadeDoBanco = this.cidadeRepository.buscar(idCidade);
        if (ObjectUtils.isEmpty(cidadeDoBanco)) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe cidade com id %s.", idCidade));
        }

        var idEstado = cidade.getEstado().getId();
        var estadoEncontrado = this.estadoRepository.findById(idEstado)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Não existe estado com id %s.", idEstado)));

        cidadeDoBanco.setEstado(estadoEncontrado);
        BeanUtils.copyProperties(cidade, cidadeDoBanco, "id", "estado");
        return this.cidadeRepository.salvar(cidadeDoBanco);
    }

    public void excluir(final Long id) {

        try {
            this.cidadeRepository.remover(id);

        } catch (EmptyResultDataAccessException ex) {
            throw new EntidadeNaoEncontradaException(String.format("Cidade com id %s não encontrada.", id));

        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(String
                .format("Cidade com id %s não pode ser removida, pois está em uso.", id));
        }
    }
}

