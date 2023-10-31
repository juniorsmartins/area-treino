package io.apirest.estacionamento.java.service;

import io.apirest.estacionamento.java.entity.Usuario;
import io.apirest.estacionamento.java.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {

        usuario.setDataCriacao(LocalDateTime.now());
        return this.usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(final Long id) {

        return this.usuarioRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Usuario editarSenha(final Long id, final String password) {

        Usuario user = this.usuarioRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
        user.setPassword(password);
        return user;
    }
}

