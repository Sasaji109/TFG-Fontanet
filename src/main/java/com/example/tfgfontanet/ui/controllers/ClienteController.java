package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.domain.servicios.ClientesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClientesService clientesService;
}
