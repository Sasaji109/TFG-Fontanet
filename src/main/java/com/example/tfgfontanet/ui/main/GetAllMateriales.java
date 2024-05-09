package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.MaterialesService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllMateriales {

    private final MaterialesService materialesService;

    @Inject
    public GetAllMateriales(MaterialesService materialesService) {
        this.materialesService = materialesService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllMateriales getAllMateriales = container.select(GetAllMateriales.class).get();
        System.out.println(getAllMateriales.materialesService.getAll());
    }
}


