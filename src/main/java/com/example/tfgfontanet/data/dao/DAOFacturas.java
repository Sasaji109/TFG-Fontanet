package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOFacturas {
    Either<DAOError, List<FacturaEntity>> getAll();
    Either<DAOError, List<FacturaEntity>> getAllByCliente(int clienteId);
    Either<DAOError, List<FacturaEntity>> getAllByProfesional(int profesionalId);
    Either<DAOError, FacturaEntity> get(int id);
    Either<DAOError, Integer> add(FacturaEntity factura);
    Either<DAOError, Integer> updateEstado(int facturaId, String estado);
}
