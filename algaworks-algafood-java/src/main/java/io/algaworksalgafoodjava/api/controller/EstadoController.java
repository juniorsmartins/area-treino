package io.algaworksalgafoodjava.api.controller;

import io.algaworksalgafoodjava.domain.model.Estado;
import io.algaworksalgafoodjava.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> listar() {
        return this.estadoRepository.listar();
    }
}

