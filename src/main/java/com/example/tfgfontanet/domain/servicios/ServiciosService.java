package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOServicios;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import com.example.tfgfontanet.domain.modelo.Servicio;
import com.example.tfgfontanet.domain.modelo.mapper.ServicioEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiciosService {

    private final DAOServicios dao;
    private final ServicioEntityMapper servicioEntityMapper;

    public Either<DAOError, List<Servicio>> getAll() {
        return dao.getAll().map(servicioEntityList -> {
            List<Servicio> servicios = new ArrayList<>();
            for (ServicioEntity servicioEntity : servicioEntityList) {
                Servicio contrato = servicioEntityMapper.toServicio(servicioEntity);
                servicios.add(contrato);
            }
            return servicios;
        });
    }

    public Either<DAOError, Servicio> get(int id) {
        return dao.get(id).map(servicioEntityMapper::toServicio);
    }

    public Boolean add(Servicio servicio) {
        try {
            dao.add(servicioEntityMapper.toServicioEntity(servicio));
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<DAOError, Integer> update(Servicio servicio) {
        ServicioEntity servicioEntity = servicioEntityMapper.toServicioEntity(servicio);
        return dao.update(servicioEntity);
    }

    public Either<DAOError, Integer> delete(int id) {
        return dao.delete(id);
    }
}
