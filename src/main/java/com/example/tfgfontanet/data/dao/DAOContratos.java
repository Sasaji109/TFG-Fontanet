package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOContratos {
    Either<DAOError, List<ContratoEntity>> getAll();
    Either<DAOError, List<ContratoEntity>> getAllByCliente(int clienteId);
    Either<DAOError, List<ContratoEntity>> getAllByProfesional(int profesionalId);
    Either<DAOError, List<ContratoEntity>> getAllByEstado(String estado);
    Either<DAOError, ContratoEntity> get(int id);
    Either<DAOError, Integer> add(ContratoEntity contrato);
    Either<DAOError, Integer> update(ContratoEntity contrato);
    Either<DAOError, Integer> updateEstado(int contratoId, String estado);
}
