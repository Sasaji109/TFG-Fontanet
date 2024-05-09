package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.FacturasService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllFacturas {

    private final FacturasService facturaService;

    @Inject
    public GetAllFacturas(FacturasService facturaService) {
        this.facturaService = facturaService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllFacturas getAllFacturas = container.select(GetAllFacturas.class).get();
        System.out.println(getAllFacturas.facturaService.getAll());
    }
}


