package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Favorito;
import com.example.tfgfontanet.domain.servicios.FavoritosService;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import jakarta.annotation.security.RolesAllowed;
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
    @RolesAllowed({Constantes.CLIENTE})
    public List<Favorito> getAllFavoritosByCliente() {
        return favoritosService.getAllByCliente().getOrElseThrow(() -> new NotFoundException(Constantes.FAVORITOS_NOT_FOUND));
    }

    @PostMapping(Constantes.FAVORITO_ADD_PATH)
    @RolesAllowed({Constantes.CLIENTE})
    public String addFavorito(@RequestParam(Constantes.PROFESIONAL_ID) Integer profesionalId) {
        if (Boolean.TRUE.equals(favoritosService.addFavorito(profesionalId))) {
            return Constantes.FAVORITO_ANADIDO_EXITOSAMENTE;
        } else {
            return Constantes.FAVORITO_NO_ANADIDO;
        }
    }

    @MutationMapping
    @RolesAllowed({Constantes.CLIENTE})
    public Integer deleteFavorito(@Argument Integer profesionalId) {
        return favoritosService.deleteFavorito(profesionalId).getOrElseThrow(() -> new CRUDException(Constantes.FAVORITO_NO_ELIMINADO));
    }
}
