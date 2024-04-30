package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.ClientesService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllClientes {

    private final ClientesService clientesService;

    @Inject
    public GetAllClientes(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllClientes getAllClientes = container.select(GetAllClientes.class).get();
        System.out.println(getAllClientes.clientesService.getAll());
    }
}


