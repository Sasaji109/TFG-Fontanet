package com.example.tfgfontanet.ui.main;

import com.example.tfgfontanet.domain.servicios.FavoritosService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;

public class GetAllFavoritosByCliente {

    private final FavoritosService favoritosService;

    @Inject
    public GetAllFavoritosByCliente(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }

    public static void main(String[] args) {
        SeContainer container = SeContainerInitializer.newInstance().initialize();
        GetAllFavoritosByCliente getAllFavoritosByCliente = container.select(GetAllFavoritosByCliente.class).get();
        System.out.println(getAllFavoritosByCliente.favoritosService.getAllByCliente(1));
    }
}
