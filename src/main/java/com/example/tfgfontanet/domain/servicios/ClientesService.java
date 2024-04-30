package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientesService {
    private final DAOClientes dao;

    @Inject
    public ClientesService(DAOClientes dao) {
        this.dao = dao;
    }
    public Either<ErrorC, List<ClienteEntity>> getAll() {
        return dao.getAll();
    }
}
