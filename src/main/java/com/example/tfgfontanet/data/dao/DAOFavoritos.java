package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOFavoritos {
    Either<DAOError, List<FavoritosEntity>> getAllByCliente(int clienteId);
    Either<DAOError, Integer> addFavorito(int clienteId, int profesionalId);
    Either<DAOError, Integer> deleteFavorito(int clienteId, int profesionalId);
}
