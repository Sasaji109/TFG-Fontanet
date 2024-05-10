package com.example.tfgfontanet.data;

import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.domain.modelo.Usuario;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;

@Repository
public class DAOUsuariosOr {

    public Usuario findByUsername(String name) {
        return BD.usuarios.stream()
                .filter(usuario -> Objects.equals(usuario.getNombre(), name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(Constantes.USER_NOT_FOUND));
    }

    public List<Usuario> getAllEmpleados() {
        return BD.usuarios.stream()
                .filter(usuario -> usuario.getRole().equalsIgnoreCase(Constantes.EMPLEADO))
                .toList();
    }
}
