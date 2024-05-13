package com.example.tfgfontanet.domain.auth;

import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.domain.mapper.UsuarioEntityMapper;
import com.example.tfgfontanet.domain.modelo.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DAOUsuario dao;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioEntityMapper usuarioEntityMapper;

    public String addEmpleado(Usuario empleado) {
        try {
            empleado.setRole("EMPLEADO");
            empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
            dao.registrarUsuario(usuarioEntityMapper.toUsuarioEntity(empleado));
            return Constantes.ADD_EMPLEADO_SUCCESS;
        } catch (Exception e) {
            return Constantes.ADD_EMPLEADO_ERROR + e.getMessage();
        }
    }
}
