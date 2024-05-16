package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Material;
import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.domain.servicios.MaterialesService;
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
public class MaterialController {

    private final MaterialesService materialesService;

    @QueryMapping
    public List<Material> getAllMateriales() {
        return materialesService.getAll().getOrElseThrow(() -> new NotFoundException("Materiales no encontrados"));
    }

    @QueryMapping
    public Material getMaterialById(@Argument Integer materialId) {
        return materialesService.get(materialId).getOrElseThrow(() -> new NotFoundException("Material no encontrado"));
    }

    @MutationMapping
    public Integer deleteMaterial(@Argument Integer materialId) {
        return materialesService.deleteMaterial(materialId).getOrElseThrow(() -> new CRUDException("Material no eliminado"));
    }

    @PostMapping("/material/add")
    public String addMaterial(@RequestBody Material material) {
        if (Boolean.TRUE.equals(materialesService.addMaterial(material))) {
            return "Material añadido exitosamente";
        } else {
            return "Material no añadido";
        }
    }

    @PutMapping("/material/update")
    public Integer updateMaterial(@RequestBody Material material) {
        return materialesService.updateMaterial(material).getOrElseThrow(() -> new CRUDException("Material no actualizado"));
    }
}