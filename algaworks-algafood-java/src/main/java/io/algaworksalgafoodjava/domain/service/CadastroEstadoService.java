package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.exception.EntidadeNaoEncontradaException;
import io.algaworksalgafoodjava.domain.model.Estado;
import io.algaworksalgafoodjava.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroEstadoService {

    private final EstadoRepository estadoRepository;

    public List<Estado> listar() {
        return this.estadoRepository.listar();
    }

    public Estado buscar(final Long id) {
        return this.estadoRepository.buscar(id);
    }

    public Estado salvar(Estado estado) {
        return this.estadoRepository.salvar(estado);
    }

    public Estado atualizar(Estado estado) {

        var idEstado = estado.getId();
        var estadoDoBanco = this.estadoRepository.buscar(idEstado);
        if (ObjectUtils.isEmpty(estadoDoBanco)) {
            throw new EntidadeNaoEncontradaException(String.format("Não existe estado com id %s.", idEstado));
        }

        BeanUtils.copyProperties(estado, estadoDoBanco, "id");
        return this.estadoRepository.salvar(estadoDoBanco);
    }
}

