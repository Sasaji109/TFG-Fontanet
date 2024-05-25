package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Servicio;
import com.example.tfgfontanet.domain.servicios.ServiciosService;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ServicioController {

    private final ServiciosService serviciosService;

    @QueryMapping
    @RolesAllowed({Constantes.ADMIN, Constantes.CLIENTE})
    public List<Servicio> getAllServicios() {
        return serviciosService.getAll().getOrElseThrow(() -> new NotFoundException(Constantes.SERVICIOS_NOT_FOUND));
    }

    @QueryMapping
    @RolesAllowed({Constantes.ADMIN, Constantes.CLIENTE})
    public Servicio getServicioById(@Argument Integer servicioId) {
        return serviciosService.get(servicioId).getOrElseThrow(() -> new NotFoundException(Constantes.SERVICIO_NOT_FOUND));
    }

    @MutationMapping
    @RolesAllowed({Constantes.ADMIN})
    public Integer deleteServicio(@Argument Integer servicioId) {
        return serviciosService.delete(servicioId).getOrElseThrow(() -> new CRUDException(Constantes.SERVICIO_NO_ELIMINADO));
    }

    @PostMapping(Constantes.SERVICIO_ADD_PATH)
    @RolesAllowed({Constantes.ADMIN})
    public String addServicio(@RequestBody Servicio servicio) {
        if (Boolean.TRUE.equals(serviciosService.add(servicio))) {
            return Constantes.SERVICIO_ANADIDO_EXITOSAMENTE;
        } else {
            return Constantes.SERVICIO_NO_ANADIDO;
        }
    }

    @PutMapping(Constantes.SERVICIO_UPDATE_PATH)
    @RolesAllowed({Constantes.ADMIN})
    public Integer updateServicio(@RequestBody Servicio servicio) {
        return serviciosService.update(servicio).getOrElseThrow(() -> new CRUDException(Constantes.SERVICIO_NO_ACTUALIZADO));
    }
}
