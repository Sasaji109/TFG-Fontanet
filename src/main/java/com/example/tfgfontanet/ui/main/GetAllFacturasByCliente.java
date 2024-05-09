package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.FacturasService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllFacturasByCliente {

    private final FacturasService facturaService;

    @Inject
    public GetAllFacturasByCliente(FacturasService facturaService) {
        this.facturaService = facturaService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllFacturasByCliente getAllFacturasByCliente = container.select(GetAllFacturasByCliente.class).get();
        System.out.println(getAllFacturasByCliente.facturaService.getAllByCliente(1));
    }
}