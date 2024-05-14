package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
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

    public Either<DAOError, List<FacturaEntity>> getAll() {
        return dao.getAll();
    }

    public Either<DAOError, List<FacturaEntity>> getAllByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId);
    }

    public Either<DAOError, List<FacturaEntity>> getAllByProfesional(int profesionalId) {
        return dao.getAllByProfesional(profesionalId);
    }

    public Either<DAOError, FacturaEntity> get(int id) {
        return dao.get(id);
    }

    public Either<DAOError, Integer> add(FacturaEntity factura) {
        return dao.add(factura);
    }

    public Either<DAOError, Integer> updateEstado(int facturaId, String estado) {
        return dao.updateEstado(facturaId, estado);
    }
}
