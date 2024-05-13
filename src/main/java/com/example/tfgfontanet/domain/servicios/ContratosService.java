package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
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

    public Either<ErrorC, List<ContratoEntity>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, List<ContratoEntity>> getAllByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId);
    }

    public Either<ErrorC, List<ContratoEntity>> getAllByProfesional(int profesionalId) {
        return dao.getAllByProfesional(profesionalId);
    }

    public Either<ErrorC, List<ContratoEntity>> getAllByEstado(String estado) {
        return dao.getAllByEstado(estado);
    }

    public Either<ErrorC, ContratoEntity> get(int id) {
        return dao.get(id);
    }

    public Either<ErrorC, Integer> add(ContratoEntity contrato) {
        return dao.add(contrato);
    }

    public Either<ErrorC, Integer> update(ContratoEntity contrato) {
        return dao.update(contrato);
    }

    public Either<ErrorC, Integer> updateEstado(int contratoId, String estado) {
        return dao.updateEstado(contratoId, estado);
    }
}
