package com.example.tfgfontanet.domain.servicios.auth;

import com.example.tfgfontanet.data.DAOUsuariosOr;
import com.example.tfgfontanet.domain.modelo.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final DAOUsuariosOr dao;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> getAllEmpleados() {
        return dao.getAllEmpleados();
    }
}
