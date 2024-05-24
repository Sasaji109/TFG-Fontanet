package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.domain.servicios.ProfesionalesService;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProfesionalController {

    private final ProfesionalesService profesionalesService;

    @QueryMapping
    @RolesAllowed({Constantes.ADMIN, Constantes.CLIENTE})
    public List<Profesional> getAllProfesionales() {
        return profesionalesService.getAll().getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados"));
    }


    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public List<Profesional> getAllProfesionalesByExp(@Argument int experiencia) {
        return profesionalesService.getAllByExp(experiencia).getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados por experiencia"));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public List<Profesional> getAllProfesionalesByOficio(@Argument String oficio) {
        return profesionalesService.getAllByOficio(oficio).getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados por oficio"));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public List<Profesional> getAllProfesionalesByDisp(@Argument String disponibilidad) {
        return profesionalesService.getAllByDisp(disponibilidad).getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados por disponibilidad"));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public List<Profesional> getAllProfesionalesByVal(@Argument int valoracion) {
        return profesionalesService.getAllByVal(valoracion).getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados por valoración"));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE, Constantes.PROFESIONAL})
    public Profesional getProfesionalById(@Argument Integer profesionalId) {
        return profesionalesService.get(profesionalId).getOrElseThrow(() -> new NotFoundException("Profesional no encontrado"));
    }

    @MutationMapping
    @RolesAllowed({Constantes.ADMIN, Constantes.PROFESIONAL})
    public Integer deleteProfesional(@Argument Integer profesionalId) {
        return profesionalesService.delete(profesionalId).getOrElseThrow(() -> new CRUDException("Profesional no eliminado"));
    }

    @PutMapping("/profesional/update")
    @RolesAllowed({Constantes.PROFESIONAL})
    public Integer updateProfesional(@RequestBody Profesional profesional) {
        return profesionalesService.update(profesional).getOrElseThrow(() -> new CRUDException("Profesional no actualizado"));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public Integer updateProfesionalVal(@Argument Integer profesionalId, @Argument Integer valoracion) {
        return profesionalesService.updateVal(profesionalId, valoracion).getOrElseThrow(() -> new NotFoundException("Valoración del profesional no actualizada"));
    }
}
