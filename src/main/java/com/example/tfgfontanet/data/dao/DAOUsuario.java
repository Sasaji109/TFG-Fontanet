package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import io.vavr.control.Either;

public interface DAOUsuario {
    UsuarioEntity findByUsername(String name);
    UsuarioEntity getUsuarioByCorreo(String correo);
    UsuarioEntity getUsuarioByCodigo(String codigo);
    Either<ErrorC, Integer> registrarUsuario(UsuarioEntity usuario);

    Either<ErrorC, Integer> updateUsuario(UsuarioEntity usuario);
}
