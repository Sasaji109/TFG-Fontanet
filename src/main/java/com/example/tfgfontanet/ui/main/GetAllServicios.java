package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.ServiciosService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllServicios {

    private final ServiciosService serviciosService;

    @Inject
    public GetAllServicios(ServiciosService serviciosService) {
        this.serviciosService = serviciosService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllServicios getAllServicios = container.select(GetAllServicios.class).get();
        System.out.println(getAllServicios.serviciosService.getAll());
    }
}


