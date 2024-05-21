package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.domain.modelo.Servicio;
import com.example.tfgfontanet.domain.servicios.ServiciosService;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
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
    public List<Servicio> getAllServicios() {
        return serviciosService.getAll().getOrElseThrow(() -> new NotFoundException("Servicios no encontrados"));
    }

    @QueryMapping
    public Servicio getServicioById(@Argument Integer servicioId) {
        return serviciosService.get(servicioId).getOrElseThrow(() -> new NotFoundException("Servicio no encontrado"));
    }

    @MutationMapping
    public Integer deleteServicio(@Argument Integer servicioId) {
        return serviciosService.delete(servicioId).getOrElseThrow(() -> new CRUDException("Servicio no eliminado"));
    }

    @PostMapping("/servicio/add")
    public String addServicio(@RequestBody Servicio servicio) {
        if (Boolean.TRUE.equals(serviciosService.add(servicio))) {
            return "Servicio añadido exitosamente";
        } else {
            return "Servicio no añadido";
        }
    }

    @PutMapping("/servicio/update")
    public Integer updateServicio(@RequestBody Servicio servicio) {
        return serviciosService.update(servicio).getOrElseThrow(() -> new CRUDException("Servicio no actualizado"));
    }
}
