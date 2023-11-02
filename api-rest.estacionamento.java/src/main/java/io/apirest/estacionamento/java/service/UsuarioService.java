package io.apirest.estacionamento.java.service;

import io.apirest.estacionamento.java.entity.Usuario;
import io.apirest.estacionamento.java.repository.UsuarioRepository;
import io.apirest.estacionamento.java.web.exception.ex.EntidadeNotFoundException;
import io.apirest.estacionamento.java.web.exception.ex.PasswordInvalidException;
import io.apirest.estacionamento.java.web.exception.ex.UsernameUniqueViolationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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

        try {
            return this.usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            throw new UsernameUniqueViolationException(String
                .format("Username {%s} já cadastrado.", usuario.getUsername()));
        }
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(final Long id) {

        return this.usuarioRepository.findById(id)
            .orElseThrow(() -> new EntidadeNotFoundException(String
                .format("Usuário id = {%s} não encontrado.", id)));
    }

    @Transactional(readOnly = true)
    public Usuario buscarPorUsername(final String username) {

        return this.usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new EntidadeNotFoundException(String
                .format("Usuário não encontrado por username: %s", username)));
    }

    @Transactional(readOnly = true)
    public Usuario.Role buscarRolePorUsername(final String username) {
        return this.usuarioRepository.findRoleByUsername(username);
    }

    @Transactional
    public void editarSenha(final Long id, final String senhaAtual, final String novaSenha, final String confirmaSenha) {

        if (!novaSenha.equals(confirmaSenha))
            throw new PasswordInvalidException(String
                .format("Nova senha = {%s} não confere com confirmação de senha = {%s}.", novaSenha, confirmaSenha));

        Usuario user = this.buscarPorId(id);

        if (!user.getPassword().equals(senhaAtual))
            throw new PasswordInvalidException(String.format("Sua senha = {%s} não confere.", senhaAtual));

        user.setPassword(novaSenha);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {

        return this.usuarioRepository.findAll();
    }
}

