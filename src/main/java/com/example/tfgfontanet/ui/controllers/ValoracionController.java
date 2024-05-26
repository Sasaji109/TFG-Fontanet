package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Valoracion;
import com.example.tfgfontanet.domain.servicios.ValoracionesService;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ValoracionController {

    private final ValoracionesService valoracionesService;

    @GetMapping(Constantes.VALORACION_PATH)
    @RolesAllowed({Constantes.PROF})
    public List<Valoracion> getValoracionesByProf() {
        return valoracionesService.getAllByProf().getOrElseThrow(() -> new NotFoundException(Constantes.VALORACIONES_NOT_FOUND));
    }

    @PostMapping(Constantes.VALORACION_ADD_PATH)
    @RolesAllowed({Constantes.CLIENTE})
    public String addValoracion(@RequestBody Valoracion valoracion) {
        if (Boolean.TRUE.equals(valoracionesService.addValoracion(1, valoracion))) {
            return Constantes.VALORACION_ANADIDA_EXITOSAMENTE;
        } else {
            return Constantes.VALORACION_NO_ANADIDA;
        }
    }
}
