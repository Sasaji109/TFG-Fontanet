package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.domain.modelo.FacturaMaterial;
import com.example.tfgfontanet.domain.servicios.FacturaMaterialService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FacturaMaterialController {

    private final FacturaMaterialService facturaMaterialService;

    @PostMapping("/facturaMaterial/add")
    public String addFacturaMaterial(@RequestBody FacturaMaterial facturaMaterial) {
        if (Boolean.TRUE.equals(facturaMaterialService.addFacturaMaterial(facturaMaterial))) {
            return "Factura de material añadida exitosamente";
        } else {
            return "Factura de material no añadida";
        }
    }
}
