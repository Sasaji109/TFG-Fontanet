package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import com.example.tfgfontanet.domain.modelo.Favorito;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FavoritoEntityMapper {
    FavoritosEntity toFavoritosEntity(Favorito favorito);
    Favorito toFavorito(FavoritosEntity favoritosEntity);
}
