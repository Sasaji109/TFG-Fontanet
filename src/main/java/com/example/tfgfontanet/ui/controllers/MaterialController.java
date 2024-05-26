package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Material;
import com.example.tfgfontanet.domain.servicios.MaterialesService;
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
public class MaterialController {

    private final MaterialesService materialesService;

    @QueryMapping
    @RolesAllowed({Constantes.ADMIN, Constantes.PROF})
    public List<Material> getAllMateriales() {
        return materialesService.getAll().getOrElseThrow(() -> new NotFoundException(Constantes.MATERIALES_NOT_FOUND));
    }

    @QueryMapping
    @RolesAllowed({Constantes.ADMIN})
    public Material getMaterialById(@Argument Integer materialId) {
        return materialesService.get(materialId).getOrElseThrow(() -> new NotFoundException(Constantes.MATERIAL_NOT_FOUND));
    }

    @MutationMapping
    @RolesAllowed({Constantes.ADMIN})
    public Integer deleteMaterial(@Argument Integer materialId) {
        return materialesService.deleteMaterial(materialId).getOrElseThrow(() -> new CRUDException(Constantes.MATERIAL_NO_ELIMINADO));
    }

    @PostMapping(Constantes.MATERIAL_ADD_PATH)
    @RolesAllowed({Constantes.ADMIN})
    public String addMaterial(@RequestBody Material material) {
        if (Boolean.TRUE.equals(materialesService.addMaterial(material))) {
            return Constantes.MATERIAL_ANADIDO_EXITOSAMENTE;
        } else {
            return Constantes.MATERIAL_NO_ANADIDO;
        }
    }

    @PutMapping(Constantes.MATERIAL_UPDATE_PATH)
    @RolesAllowed({Constantes.ADMIN})
    public Integer updateMaterial(@RequestBody Material material) {
        return materialesService.updateMaterial(material).getOrElseThrow(() -> new CRUDException(Constantes.MATERIAL_NO_ACTUALIZADO));
    }
}