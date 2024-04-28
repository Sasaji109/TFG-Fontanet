package com.example.tfgfontanet.ui.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class RestAuth { /*

    private final AuthService service;
    private final JwtService jwtService;

    @GetMapping(Constantes.LOGINPATH)
    public AuthenticationResponse loginAuth(@RequestParam(Constantes.USERNAME) String username, @RequestParam(Constantes.PASSWORD) String password) {
        AuthenticationRequest requestAuth = new AuthenticationRequest(username, password);
        return service.authenticate(requestAuth);
    }

    @GetMapping("/refresh")
    public String refreshToken(@RequestParam("token") String token) {
        return jwtService.renovarAccessToken(token).get();
    } */
}
