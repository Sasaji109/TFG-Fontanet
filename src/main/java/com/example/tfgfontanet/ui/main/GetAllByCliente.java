package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.ContratosService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllByCliente {

    private final ContratosService contratosService;

    @Inject
    public GetAllByCliente(ContratosService contratosService) {
        this.contratosService = contratosService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllByCliente getAllByCliente = container.select(GetAllByCliente.class).get();
        System.out.println(getAllByCliente.contratosService.getAllByCliente(1));
    }
}
