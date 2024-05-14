package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOFavoritos;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritosService {

    private final DAOFavoritos dao;

    public Either<DAOError, List<FavoritosEntity>> getAllByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId);
    }

    public Either<DAOError, Integer> addFavorito(int clienteId, int profesionalId) {
        return dao.addFavorito(clienteId, profesionalId);
    }

    public Either<DAOError, Integer> deleteFavorito(int clienteId, int profesionalId) {
        return dao.deleteFavorito(clienteId, profesionalId);
    }
}
