package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOFacturas {
    Either<ErrorC, List<FacturaEntity>> getAll();
    Either<ErrorC, List<FacturaEntity>> getAllByCliente(int clienteId);
    Either<ErrorC, List<FacturaEntity>> getAllByProfesional(int profesionalId);
    Either<ErrorC, FacturaEntity> get(int id);
    Either<ErrorC, Integer> add(FacturaEntity factura);
    Either<ErrorC, Integer> updateEstado(int facturaId, String estado);
}
