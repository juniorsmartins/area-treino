package io.algaworksalgafoodjava.domain.service;

import io.algaworksalgafoodjava.domain.model.Cidade;
import io.algaworksalgafoodjava.domain.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;

    public List<Cidade> listar() {
        return this.cidadeRepository.listar();
    }

    public Cidade buscar(final Long id) {
        return this.cidadeRepository.buscar(id);
    }
}

