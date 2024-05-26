package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOFacturas {
    Either<CustomError, List<FacturaEntity>> getAll();
    Either<CustomError, List<FacturaEntity>> getAllByCliente(int clienteId);
    Either<CustomError, List<FacturaEntity>> getAllByProfesional(int profesionalId);
    Either<CustomError, FacturaEntity> get(int id);
    Either<CustomError, FacturaEntity> add(FacturaEntity factura);
    Either<CustomError, Integer> updateEstado(int facturaId, String estado);
}
