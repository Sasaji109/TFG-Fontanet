package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOContratos;
import com.example.tfgfontanet.data.modelo.ContratoEntity;
import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.modelo.Contrato;
import com.example.tfgfontanet.domain.modelo.mapper.ContratoEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContratosService {

    private final DAOContratos dao;
    private final ContratoEntityMapper contratoEntityMapper;

    public Either<DAOError, List<Contrato>> getAll() {
        return dao.getAll().map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<DAOError, List<Contrato>> getContratosByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId).map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<DAOError, List<Contrato>> getContratosByProfesional(int profesionalId) {
        return dao.getAllByProfesional(profesionalId).map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<DAOError, List<Contrato>> getContratosByEstado(String estado) {
        return dao.getAllByEstado(estado).map(contratoEntityList -> {
            List<Contrato> contratos = new ArrayList<>();
            for (ContratoEntity contratoEntity : contratoEntityList) {
                Contrato contrato = contratoEntityMapper.toContrato(contratoEntity);
                contratos.add(contrato);
            }
            return contratos;
        });
    }

    public Either<DAOError, Contrato> get(int id) {
        return dao.get(id).map(contratoEntityMapper::toContrato);
    }

    public Boolean add(Contrato contrato) {
        try {
            Cliente cliente = new Cliente();
            cliente.setClienteId(1);
            contrato.setCliente(cliente);
            dao.add(contratoEntityMapper.toContratoEntity(contrato));
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<DAOError, Integer> update(Contrato contrato) {
        ContratoEntity contratoEntity = contratoEntityMapper.toContratoEntity(contrato);
        return dao.update(contratoEntity);
    }

    public Either<DAOError, Integer> updateEstado(int contratoId, String estado) {
        return dao.updateEstado(contratoId, estado);
    }
}
