package com.desafiov2picpayjava.adapters.out;

import com.desafiov2picpayjava.adapters.out.mappers.UsuarioOrmMapper;
import com.desafiov2picpayjava.adapters.out.repositories.UsuarioRepository;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.out.UsuarioBuscarPorIdOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class UsuarioBuscarPorIdAdapter implements UsuarioBuscarPorIdOutputPort {

    private final Logger logger = Logger.getLogger(UsuarioBuscarPorIdAdapter.class.getName());

    private final UsuarioRepository usuarioRepository;

    private final UsuarioOrmMapper usuarioOrmMapper;

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> buscarPorId(final Long id) {

        logger.info("Adapter - iniciado buscar Usuário por id no banco de dados.");

        var usuarioBuscado = this.usuarioRepository.findById(id)
            .map(this.usuarioOrmMapper::toUsuario);

        logger.info("Adapter - concluído buscar Usuário por id no banco de dados.");

        return usuarioBuscado;
    }
}

