package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.domain.modelo.Favorito;
import com.example.tfgfontanet.domain.servicios.FavoritosService;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FavoritosController {

    private final FavoritosService favoritosService;

    @QueryMapping
    public List<Favorito> getAllFavoritosByCliente(@Argument int clienteId) {
        return favoritosService.getAllByCliente(clienteId).getOrElseThrow(() -> new NotFoundException("Favoritos no encontrados"));
    }

    @PostMapping("/favoritos/add")
    public String addFavorito(@RequestParam("clienteId") Integer clienteId, @RequestParam("profesionalId") Integer profesionalId) {
        if (Boolean.TRUE.equals(favoritosService.addFavorito(clienteId, profesionalId))) {
            return "Favorito añadido exitosamente";
        } else {
            return "Favorito no añadido";
        }
    }

    @MutationMapping
    public Integer deleteFavorito(@Argument Integer clienteId, @Argument Integer profesionalId) {
        return favoritosService.deleteFavorito(clienteId, profesionalId).getOrElseThrow(() -> new CRUDException("Favorito no eliminado"));
    }
}
