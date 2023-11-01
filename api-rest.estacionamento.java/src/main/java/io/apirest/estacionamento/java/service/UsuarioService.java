package io.apirest.estacionamento.java.service;

import io.apirest.estacionamento.java.entity.Usuario;
import io.apirest.estacionamento.java.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public Usuario editarSenha(final Long id, final String senhaAtual, final String novaSenha, final String confirmaSenha) {

        if (!novaSenha.equals(confirmaSenha))
            throw new RuntimeException("Nova senha não confere com confirmação de senha.");

        Usuario user = this.usuarioRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);

        if (!user.getPassword().equals(senhaAtual))
            throw new RuntimeException("Sua senha não confere.");

        user.setPassword(novaSenha);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {

        return this.usuarioRepository.findAll();
    }
}

