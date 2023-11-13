package com.desafiov2picpayjava.adapters.out;

import com.desafiov2picpayjava.adapters.out.mappers.UsuarioOrmMapper;
import com.desafiov2picpayjava.adapters.out.repositories.UsuarioRepository;
import com.desafiov2picpayjava.application.core.domain.Usuario;
import com.desafiov2picpayjava.application.ports.out.UsuarioBuscarTodosOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class UsuarioBuscarTodosAdapter implements UsuarioBuscarTodosOutputPort {

    private final Logger logger = Logger.getLogger(UsuarioBuscarTodosAdapter.class.getName());

    private final UsuarioRepository usuarioRepository;

    private final UsuarioOrmMapper usuarioOrmMapper;

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> buscarTodos() {

        this.logger.info("Adapter - iniciado buscar todos os Usuários no banco de dados.");

        var usuarios = this.usuarioRepository.findAll()
            .stream()
            .map(this.usuarioOrmMapper::toUsuario)
            .toList();

        this.logger.info("Adapter - concluído buscar todos os Usuários no banco de dados.");

        return usuarios;
    }
}

