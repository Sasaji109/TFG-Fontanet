package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.servicios.UserService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(Constantes.MOVILESPATH)
public class RestMoviles {

    private final UserService service; /*

    @GetMapping()
    //@RolesAllowed({Constantes.ADMIN, Constantes.EMPLEADO})
    public List<Movil> getAllMoviles(@RequestParam(Constantes.ROLE) String role, @RequestParam(Constantes.USERNAME) String username) {
        return service.getAllMoviles(role, username);
    }

    @PostMapping(Constantes.UPDATEPATH)
    @RolesAllowed({Constantes.EMPLEADO})
    public String actualizarMovil(@RequestBody Movil movil) {
        return service.actualizarMovil(movil);
    }

    @DeleteMapping(Constantes.DELETEPATH)
    @RolesAllowed({Constantes.EMPLEADO})
    public String eliminarMovil(@RequestBody Movil movil) {
        return service.borrarMovil(movil);
    }

    @PostMapping(Constantes.ADDPATH)
    @RolesAllowed({Constantes.EMPLEADO})
    public String addMovil(@RequestBody Movil movil) {
        return service.addMovil(movil);
    } */
}
