package io.apirest.estacionamento.java.service;

import io.apirest.estacionamento.java.entity.Cliente;
import io.apirest.estacionamento.java.repository.ClienteRepository;
import io.apirest.estacionamento.java.web.exception.ex.CpfUniqueViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) throws CpfUniqueViolationException {

        try {
            return this.clienteRepository.save(cliente);

        } catch (DataIntegrityViolationException ex) {
            throw new CpfUniqueViolationException(String
                .format("CPF '%s' não pode ser cadastrado, pois já existe no sistema.", cliente.getCpf()));
        }
    }
}

