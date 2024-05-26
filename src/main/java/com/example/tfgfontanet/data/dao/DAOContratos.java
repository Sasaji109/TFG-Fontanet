package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOContratos {
    Either<CustomError, List<ContratoEntity>> getAll();
    Either<CustomError, List<ContratoEntity>> getAllByCliente(int clienteId);
    Either<CustomError, List<ContratoEntity>> getAllByProfesional(int profesionalId);
    Either<CustomError, List<ContratoEntity>> getAllByEstado(String estado);
    Either<CustomError, ContratoEntity> get(int id);
    Either<CustomError, ContratoEntity> add(ContratoEntity contrato);
    Either<CustomError, Integer> update(ContratoEntity contrato);
    Either<CustomError, Integer> updateEstado(int contratoId, String estado);
}
