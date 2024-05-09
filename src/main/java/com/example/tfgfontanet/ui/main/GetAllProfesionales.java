package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.ProfesionalesService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllProfesionales {

    private final ProfesionalesService profesionalesService;

    @Inject
    public GetAllProfesionales(ProfesionalesService profesionalesService) {
        this.profesionalesService = profesionalesService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllProfesionales getAllProfesionales = container.select(GetAllProfesionales.class).get();
        System.out.println(getAllProfesionales.profesionalesService.getAll());
    }
}


