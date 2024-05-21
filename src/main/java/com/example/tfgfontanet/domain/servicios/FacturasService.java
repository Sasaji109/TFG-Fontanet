package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOFacturas;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import com.example.tfgfontanet.domain.modelo.Factura;
import com.example.tfgfontanet.domain.modelo.mapper.FacturaEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturasService {

    private final DAOFacturas dao;
    private final FacturaEntityMapper facturaEntityMapper;

    public Either<DAOError, List<Factura>> getAll() {
        return dao.getAll().map(facturaEntityList -> {
            List<Factura> facturas = new ArrayList<>();
            for (FacturaEntity facturaEntity : facturaEntityList) {
                Factura cliente = facturaEntityMapper.toFactura(facturaEntity);
                facturas.add(cliente);
            }
            return facturas;
        });
    }

    public Either<DAOError, List<Factura>> getFacturasByCliente(int clienteId) {
        return dao.getAllByCliente(clienteId).map(facturaEntityList -> {
            List<Factura> facturas = new ArrayList<>();
            for (FacturaEntity facturaEntity : facturaEntityList) {
                Factura factura = facturaEntityMapper.toFactura(facturaEntity);
                facturas.add(factura);
            }
            return facturas;
        });
    }

    public Either<DAOError, List<Factura>> getFacturasByProfesional(int profesionalId) {
        return dao.getAllByProfesional(profesionalId).map(facturaEntityList -> {
            List<Factura> facturas = new ArrayList<>();
            for (FacturaEntity facturaEntity : facturaEntityList) {
                Factura factura = facturaEntityMapper.toFactura(facturaEntity);
                facturas.add(factura);
            }
            return facturas;
        });
    }

    public Either<DAOError, Factura> get(int id) {
        return dao.get(id).map(facturaEntityMapper::toFactura);
    }

    public Boolean add(Factura factura) {
        try {
            dao.add(facturaEntityMapper.toFacturaEntity(factura));
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<DAOError, Integer> updateEstado(int facturaId, String estado) {
        return dao.updateEstado(facturaId, estado);
    }
}
