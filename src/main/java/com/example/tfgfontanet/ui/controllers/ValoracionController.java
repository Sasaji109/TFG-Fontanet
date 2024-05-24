package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Valoracion;
import com.example.tfgfontanet.domain.servicios.ValoracionesService;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ValoracionController {

    private final ValoracionesService valoracionesService;

    @GetMapping("/valoracion/{profesionalId}")
    @RolesAllowed({Constantes.PROFESIONAL})
    public List<Valoracion> getValoracionesByProf(@PathVariable("profesionalId") Integer profesionalId) {
        return valoracionesService.getAllByProf(profesionalId).getOrElseThrow(() -> new NotFoundException("Valoraciones no encontradas"));
    }

    @PostMapping("/valoracion/add")
    @RolesAllowed({Constantes.CLIENTE})
    public String addValoracion(@RequestBody Valoracion valoracion) {
        if (Boolean.TRUE.equals(valoracionesService.addValoracion(1, valoracion))) {
            return "Valoración añadida exitosamente";
        } else {
            return "Valoración no añadida";
        }
    }
}
