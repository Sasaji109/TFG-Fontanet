package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import java.util.List;

public class ClientesService {
    private final DAOClientes dao;

    @Inject
    public ClientesService(DAOClientes dao) {
        this.dao = dao;
    }

    public Either<ErrorC, List<ClienteEntity>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, ClienteEntity> get(int id) {
        return dao.get(id);
    }

    public Either<ErrorC, Integer> add(ClienteEntity cliente) {
        return dao.add(cliente);
    }

    public Either<ErrorC, Integer> update(ClienteEntity cliente) {
        return dao.update(cliente);
    }

    public Either<ErrorC, Integer> delete(int id) {
        return dao.delete(id);
    }
}
