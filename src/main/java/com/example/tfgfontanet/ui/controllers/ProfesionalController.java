package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.domain.servicios.ProfesionalesService;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
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
    public List<Profesional> getAllProfesionales() {
        return profesionalesService.getAll().getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados"));
    }


    @QueryMapping
    public List<Profesional> getAllProfesionalesByExp(@Argument int experiencia) {
        return profesionalesService.getAllByExp(experiencia).getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados por experiencia"));
    }

    @QueryMapping
    public List<Profesional> getAllProfesionalesByOficio(@Argument String oficio) {
        return profesionalesService.getAllByOficio(oficio).getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados por oficio"));
    }

    @QueryMapping
    public List<Profesional> getAllProfesionalesByDisp(@Argument String disponibilidad) {
        return profesionalesService.getAllByDisp(disponibilidad).getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados por disponibilidad"));
    }

    @QueryMapping
    public List<Profesional> getAllProfesionalesByVal(@Argument int valoracion) {
        return profesionalesService.getAllByVal(valoracion).getOrElseThrow(() -> new NotFoundException("Profesionales no encontrados por valoración"));
    }

    @QueryMapping
    public Profesional getProfesionalById(@Argument Integer profesionalId) {
        return profesionalesService.get(profesionalId).getOrElseThrow(() -> new NotFoundException("Profesional no encontrado"));
    }

    @MutationMapping
    public Integer deleteProfesional(@Argument Integer profesionalId) {
        return profesionalesService.delete(profesionalId).getOrElseThrow(() -> new CRUDException("Profesional no eliminado"));
    }

    @PutMapping("/profesional/update")
    public Integer updateProfesional(@RequestBody Profesional profesional) {
        return profesionalesService.update(profesional).getOrElseThrow(() -> new CRUDException("Profesional no actualizado"));
    }

    @QueryMapping
    public Integer updateProfesionalVal(@Argument Integer profesionalId, @Argument Integer valoracion) {
        return profesionalesService.updateVal(profesionalId, valoracion).getOrElseThrow(() -> new NotFoundException("Valoración del profesional no actualizada"));
    }
}
