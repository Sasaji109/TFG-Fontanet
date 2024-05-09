package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOFavoritos;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import java.util.List;

public class FavoritosService {
    private final DAOFavoritos dao;

    @Inject
    public FavoritosService(DAOFavoritos dao) {
        this.dao = dao;
    }

    public Either<ErrorC, List<FavoritosEntity>> getAllByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId);
    }

    public Either<ErrorC, Integer> addFavorito(int clienteId, int profesionalId) {
        return dao.addFavorito(clienteId, profesionalId);
    }

    public Either<ErrorC, Integer> deleteFavorito(int clienteId, int profesionalId) {
        return dao.deleteFavorito(clienteId, profesionalId);
    }
}