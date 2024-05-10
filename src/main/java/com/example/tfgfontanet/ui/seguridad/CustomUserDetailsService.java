package com.example.tfgfontanet.ui.seguridad;

import com.example.tfgfontanet.data.DAOUsuariosOr;
import com.example.tfgfontanet.domain.modelo.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DAOUsuariosOr dao;

    public CustomUserDetailsService(DAOUsuariosOr dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = dao.findByUsername(username);

        return User.builder()
                .username(user.getNombre())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
