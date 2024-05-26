package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.FacturaMaterial;
import com.example.tfgfontanet.domain.servicios.FacturaMaterialService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FacturaMaterialController {

    private final FacturaMaterialService facturaMaterialService;

    @PostMapping(Constantes.FACTURAMATERIAL_ADD_PATH)
    @RolesAllowed({Constantes.PROF})
    public String addFacturaMaterial(@RequestBody FacturaMaterial facturaMaterial) {
        if (Boolean.TRUE.equals(facturaMaterialService.addFacturaMaterial(facturaMaterial))) {
            return Constantes.FACTURA_DE_MATERIAL_ANADIDA_EXITOSAMENTE;
        } else {
            return Constantes.FACTURA_DE_MATERIAL_NO_ANADIDA;
        }
    }
}
