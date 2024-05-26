package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOClientes {
    Either<CustomError, List<ClienteEntity>> getAll();
    Either<CustomError, ClienteEntity> get(int id);
    Either<CustomError, ClienteEntity> getByUserId(int userId);
    Either<CustomError, Integer> add(ClienteEntity cliente);
    Either<CustomError, Integer> update(ClienteEntity cliente);
    Either<CustomError, Integer> delete(int id);
}
