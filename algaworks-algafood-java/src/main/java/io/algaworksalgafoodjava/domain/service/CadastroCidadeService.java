package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Cidade;
import io.algaworksalgafoodjava.domain.repository.CidadeRepository;
import io.algaworksalgafoodjava.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        var estadoDoBanco = this.estadoRepository.buscar(idEstado);
        if (ObjectUtils.isEmpty(estadoDoBanco)) {
            throw new IllegalArgumentException(String.format("Não existe estado com id %s.", idEstado));
        }

        cidadeDoBanco.setEstado(estadoDoBanco);
        BeanUtils.copyProperties(cidade, cidadeDoBanco, "id", "estado");
        return this.cidadeRepository.salvar(cidadeDoBanco);
    }
}

