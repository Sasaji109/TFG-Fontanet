package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOContratos;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratosService {

    private final DAOContratos dao;

    public Either<DAOError, List<ContratoEntity>> getAll() {
        return dao.getAll();
    }

    public Either<DAOError, List<ContratoEntity>> getAllByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId);
    }

    public Either<DAOError, List<ContratoEntity>> getAllByProfesional(int profesionalId) {
        return dao.getAllByProfesional(profesionalId);
    }

    public Either<DAOError, List<ContratoEntity>> getAllByEstado(String estado) {
        return dao.getAllByEstado(estado);
    }

    public Either<DAOError, ContratoEntity> get(int id) {
        return dao.get(id);
    }

    public Either<DAOError, Integer> add(ContratoEntity contrato) {
        return dao.add(contrato);
    }

    public Either<DAOError, Integer> update(ContratoEntity contrato) {
        return dao.update(contrato);
    }

    public Either<DAOError, Integer> updateEstado(int contratoId, String estado) {
        return dao.updateEstado(contratoId, estado);
    }
}
