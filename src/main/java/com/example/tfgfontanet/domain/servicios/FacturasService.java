package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOFacturas;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class FacturasService {
    private final DAOFacturas dao;

    @Inject
    public FacturasService(DAOFacturas dao) {
        this.dao = dao;
    }

    public Either<ErrorC, List<FacturaEntity>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, List<FacturaEntity>> getAllByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId);
    }

    public Either<ErrorC, List<FacturaEntity>> getAllByProfesional(int profesionalId) {
        return dao.getAllByProfesional(profesionalId);
    }

    public Either<ErrorC, FacturaEntity> get(int id) {
        return dao.get(id);
    }

    public Either<ErrorC, Integer> add(FacturaEntity factura) {
        return dao.add(factura);
    }

    public Either<ErrorC, Integer> updateEstado(int facturaId, String estado) {
        return dao.updateEstado(facturaId, estado);
    }
}
