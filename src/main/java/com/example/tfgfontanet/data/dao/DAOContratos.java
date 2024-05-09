package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOContratos {
    Either<ErrorC, List<ContratoEntity>> getAll();
    Either<ErrorC, List<ContratoEntity>> getAllByCliente(int clienteId);
    Either<ErrorC, List<ContratoEntity>> getAllByProfesional(int profesionalId);
    Either<ErrorC, List<ContratoEntity>> getAllByEstado(String estado);
    Either<ErrorC, ContratoEntity> get(int id);
    Either<ErrorC, Integer> add(ContratoEntity contrato);
    Either<ErrorC, Integer> update(ContratoEntity contrato);
    Either<ErrorC, Integer> updateEstado(int contratoId, String estado);
}
