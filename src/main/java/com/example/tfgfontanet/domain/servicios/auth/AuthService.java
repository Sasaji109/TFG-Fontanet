package com.example.tfgfontanet.domain.servicios.auth;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.Usuario;
import com.example.tfgfontanet.domain.modelo.auth.AuthenticationRequest;
import com.example.tfgfontanet.domain.modelo.auth.AuthenticationResponse;
import com.example.tfgfontanet.domain.modelo.mapper.UsuarioEntityMapper;
import com.example.tfgfontanet.domain.servicios.mail.MandarMailActivacion;
import com.example.tfgfontanet.ui.errores.excepciones.UsuarioNoActivadoException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final DAOUsuario dao;
    private final UsuarioEntityMapper usuarioEntityMapper;
    private final MandarMailActivacion mandarMailActivacion;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsuarioEntity usuarioEntity = dao.findByUsername(request.username()).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
        Usuario usuario = usuarioEntityMapper.toUsuario(usuarioEntity);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = userDetailsService.loadUserByUsername(request.username());

        if (Boolean.FALSE.equals(usuario.getActivado())) {
            mandarMailActivacion.mandarMail(usuario);
            throw new UsuarioNoActivadoException(Constantes.ENVIADO_UN_CORREO_DE_ACTIVACION);
        }

        var jwtToken = jwtService.generateToken(user.getUsername(), Constantes.ACCESS_TOKEN_TIME).get();
        var refreshToken = jwtService.generateToken(user.getUsername(), Constantes.REFRESH_TOKEN_TIME).get();
        var role = usuario.getRole();

        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).role(role).build();
    }
}
