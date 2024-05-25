package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import io.vavr.control.Either;
import java.util.Optional;

public interface DAOUsuario {
    Optional<UsuarioEntity> findByUsername(String name);
    UsuarioEntity getUsuarioByCorreo(String correo);
    UsuarioEntity getUsuarioByCodigo(String codigo);
    Either<DAOError, Integer> updateUsuario(UsuarioEntity usuario);
}
