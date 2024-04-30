package com.example.tfgfontanet.ui.seguridad;

import com.example.tfgfontanet.data.dao.implementaciones.DAOUsuariosImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DAOUsuariosImpl daoUsuariosImpl;

    public CustomUserDetailsService(DAOUsuariosImpl daoUsuariosImpl) {
        this.daoUsuariosImpl = daoUsuariosImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/*
        Usuario user = daoUsuariosImpl.findByUsername(username);

        return User.builder()
                .username(user.getNombre())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();*/
        return null;
    }
}
