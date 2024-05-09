package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.ValoracionesService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllValoracionesByProf {

    private final ValoracionesService valoracionesService;

    @Inject
    public GetAllValoracionesByProf(ValoracionesService valoracionesService) {
        this.valoracionesService = valoracionesService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllValoracionesByProf getAllValoracionesByProf = container.select(GetAllValoracionesByProf.class).get();
        System.out.println(getAllValoracionesByProf.valoracionesService.getAllByProf(1));
    }
}


