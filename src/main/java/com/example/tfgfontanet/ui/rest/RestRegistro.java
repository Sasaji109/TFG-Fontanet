package com.example.tfgfontanet.ui.rest;

import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.domain.servicios.ClientesService;
import com.example.tfgfontanet.domain.servicios.ProfesionalesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/registro")
public class RestRegistro {

    private final ClientesService clientesService;
    private final ProfesionalesService profesionalesService;

    @PostMapping("/cliente")
    public Integer registroCliente(@RequestBody Cliente cliente) {
        return clientesService.registroCliente(cliente).getOrElse(0);
    }

    @PostMapping("/profesional")
    public Integer registroProfesional(@RequestBody Profesional profesional) {
        return profesionalesService.registroProfesional(profesional).getOrElse(0);
    }
}
