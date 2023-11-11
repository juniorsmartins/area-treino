package com.desafiov2picpayjava.adapters.out;

import com.desafiov2picpayjava.adapters.out.mappers.UsuarioOrmMapper;
import com.desafiov2picpayjava.adapters.out.repositories.UsuarioRepository;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.out.UsuarioSalvarOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class UsuarioSalvarAdapter implements UsuarioSalvarOutputPort {

    private final Logger logger = Logger.getLogger(UsuarioSalvarAdapter.class.getName());

    private final UsuarioRepository usuarioRepository;

    private final UsuarioOrmMapper usuarioOrmMapper;

    @Transactional
    @Override
    public Usuario salvar(Usuario usuario) {

        logger.info("Adapter - iniciada persistência de Usuário no banco de dados.");

        var usuarioSalvo = Optional.of(usuario)
            .map(this.usuarioOrmMapper::toUsuarioOrm)
            .map(this.usuarioRepository::save)
            .map(this.usuarioOrmMapper::toUsuario)
            .orElseThrow(NoSuchElementException::new);

        logger.info("Adapter - concluída persistência de Usuário no banco de dados.");

        return usuarioSalvo;
    }
}

