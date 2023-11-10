package com.desafiov2picpayjava.adapters.out;

import com.desafiov2picpayjava.adapters.out.mappers.UsuarioOrmMapper;
import com.desafiov2picpayjava.adapters.out.repositories.UsuarioRepository;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.out.UsuarioBuscarPorIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class UsuarioBuscarPorIdAdapter implements UsuarioBuscarPorIdOutputPort {

    private final Logger logger = Logger.getLogger(UsuarioBuscarPorIdAdapter.class.getName());

    private final UsuarioRepository usuarioRepository;

    private final UsuarioOrmMapper usuarioOrmMapper;

    @Transactional(readOnly = true)
    @Override
    public Usuario buscarPorId(final Long id) {

        logger.info("Adapter - iniciada buscar por id de Usuário no banco de dados.");

        var usuarioBuscado = this.usuarioRepository.findById(id)
            .map(this.usuarioOrmMapper::toUsuario)
            .orElseThrow(NoSuchElementException::new);

        logger.info("Adapter - concluída busca por id de Usuário no banco de dados.");

        return usuarioBuscado;
    }
}

