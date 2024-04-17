package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Usuario;
import com.example.tfgfontanet.domain.servicios.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(Constantes.EMPLEADOSPATH)
public class RestEmpleados {

    private final UserService service;

    @GetMapping()
    @RolesAllowed({Constantes.ADMIN})
    public List<Usuario> getAllEmpleados() {
        return service.getAllEmpleados();
    }

    @PostMapping()
    @RolesAllowed({Constantes.ADMIN})
    public String addEmpleado(@RequestBody Usuario empleado) {
        return service.addEmpleado(empleado);
    }
}
