package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.repositorios.DAOUsuarios;
import com.example.tfgfontanet.domain.modelo.Usuario;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DAOUsuarios dao;
    private final PasswordEncoder passwordEncoder; /*

    public String addEmpleado(Usuario empleado) {
        try {
            empleado.setRole(Constantes.EMPLEADO);
            List<Movil> moviles = new ArrayList<>();
            empleado.setMoviles(moviles);
            empleado.setPassword(passwordEncoder.encode(empleado.getPassword()));
            dao.addEmpleado(empleado);
            return Constantes.ADD_EMPLEADO_SUCCESS;
        } catch (Exception e) {
            return Constantes.ADD_EMPLEADO_ERROR + e.getMessage();
        }
    }

    public List<Usuario> getAllEmpleados() {
        return dao.getAllEmpleados();
    }

    public List<Movil> getAllMoviles(String role, String username) {
        return dao.getAllMoviles(role, username);
    }

    public String actualizarMovil(Movil movil) {
        try {
            Integer resultado = dao.actualizarMovil(movil);
            if (resultado == 1) {
                return Constantes.UPDATE_MOVIL_SUCCESS;
            } else {
                throw new NotFoundException(Constantes.EMPLEADO_NOT_FOUND);
            }
        } catch (Exception e) {
            return Constantes.UPDATE_MOVIL_ERROR + e.getMessage();
        }
    }

    public String borrarMovil(Movil movil) {

        try {
            Integer resultado = dao.borrarMovil(movil);
            if (resultado == 1) {
                return Constantes.DELETE_MOVIL_SUCCESS;
            } else {
                throw new NotFoundException(Constantes.EMPLEADO_NOT_FOUND);
            }
        } catch (Exception e) {
            return Constantes.DELETE_MOVIL_ERROR + e.getMessage();
        }
    }

    public String addMovil(Movil movil) {

        try {
            Integer resultado = dao.addMovil(movil);
            if (resultado == 1) {
                return Constantes.ADD_MOVIL_SUCCESS;
            } else {
                throw new NotFoundException(Constantes.EMPLEADO_NOT_FOUND);
            }
        } catch (Exception e) {
            return Constantes.ADD_MOVIL_ERROR + e.getMessage();
        }
    } */
}
