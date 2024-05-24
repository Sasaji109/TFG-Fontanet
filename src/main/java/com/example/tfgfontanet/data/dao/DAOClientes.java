package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOClientes {
    Either<DAOError, List<ClienteEntity>> getAll();
    Either<DAOError, ClienteEntity> get(int id);
    Either<DAOError, ClienteEntity> getByUserId(int userId);
    Either<DAOError, Integer> add(ClienteEntity cliente);
    Either<DAOError, Integer> update(ClienteEntity cliente);
    Either<DAOError, Integer> delete(int id);
}
