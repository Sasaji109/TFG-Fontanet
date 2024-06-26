package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOFavoritos {
    Either<CustomError, List<FavoritosEntity>> getAllByCliente(int clienteId);
    Either<CustomError, Integer> addFavorito(int clienteId, int profesionalId);
    Either<CustomError, Integer> deleteFavorito(int clienteId, int profesionalId);
}
