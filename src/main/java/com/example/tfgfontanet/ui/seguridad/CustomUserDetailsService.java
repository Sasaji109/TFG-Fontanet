package com.example.tfgfontanet.ui.seguridad;

import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.mapper.UsuarioEntityMapper;
import com.example.tfgfontanet.domain.modelo.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final DAOUsuario daoUsuarios;
    private final UsuarioEntityMapper usuarioEntityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioEntity usuario = daoUsuarios.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));;
        Usuario user = usuarioEntityMapper.toUsuario(usuario);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
