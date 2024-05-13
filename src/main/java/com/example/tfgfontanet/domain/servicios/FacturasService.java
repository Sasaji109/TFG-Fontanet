package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOFacturas;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturasService {

    private final DAOFacturas dao;

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
