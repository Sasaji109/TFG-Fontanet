package com.example.tfgfontanet.ui.seguridad;

import com.example.exampsp2eva.dao.repositorios.DAOUsuarios;
import com.example.exampsp2eva.domain.modelo.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DAOUsuarios daoUsuarios;

    public CustomUserDetailsService(DAOUsuarios daoUsuarios) {
        this.daoUsuarios = daoUsuarios;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = daoUsuarios.findByUsername(username);

        return User.builder()
                .username(user.getNombre())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

    }
}
