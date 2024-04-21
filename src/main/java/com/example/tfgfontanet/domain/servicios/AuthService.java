package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.auth.AuthenticationRequest;
import com.example.tfgfontanet.domain.modelo.auth.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }
/*
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        var user = userDetailsService.loadUserByUsername(request.username());

        var jwtToken = jwtService.generateToken(user.getUsername(), Constantes.ACCESS_TOKEN_TIME).get();
        var refreshToken = jwtService.generateToken(user.getUsername(), Constantes.REFRESH_TOKEN_TIME).get();
        return AuthenticationResponse.builder()
                .accessToken(jwtToken).refreshToken(refreshToken).build();
    } */
}
