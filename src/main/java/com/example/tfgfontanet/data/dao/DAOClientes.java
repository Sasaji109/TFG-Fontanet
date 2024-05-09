package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOClientes {
    Either<ErrorC, List<ClienteEntity>> getAll();
    Either<ErrorC, ClienteEntity> get(int id);
    Either<ErrorC, Integer> add(ClienteEntity cliente);
    Either<ErrorC, Integer> update(ClienteEntity cliente);
    Either<ErrorC, Integer> delete(int id);
}
