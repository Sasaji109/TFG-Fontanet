package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOFavoritos;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import com.example.tfgfontanet.domain.modelo.Favorito;
import com.example.tfgfontanet.domain.modelo.mapper.FavoritoEntityMapper;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritosService {

    private final DAOFavoritos dao;
    private final FavoritoEntityMapper favoritoEntityMapper;

    public Either<DAOError, List<Favorito>> getAllByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId).map(favoritoEntityList -> {
            List<Favorito> favoritos = new ArrayList<>();
            for (FavoritosEntity favoritoEntity : favoritoEntityList) {
                Favorito favorito = favoritoEntityMapper.toFavorito(favoritoEntity);
                favoritos.add(favorito);
            }
            return favoritos;
        });
    }

    public Either<DAOError, Integer> addFavorito(int clienteId, int profesionalId) {
        return dao.addFavorito(clienteId, profesionalId);
    }

    public Either<DAOError, Integer> deleteFavorito(int clienteId, int profesionalId) {
        return dao.deleteFavorito(clienteId, profesionalId);
    }
}
