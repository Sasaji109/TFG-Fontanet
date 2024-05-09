package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOServicios;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import java.util.List;

public class ServiciosService {
    private final DAOServicios dao;

    @Inject
    public ServiciosService(DAOServicios dao) {
        this.dao = dao;
    }

    public Either<ErrorC, List<ServicioEntity>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, ServicioEntity> get(int id) {
        return dao.get(id);
    }

    public Either<ErrorC, Integer> add(ServicioEntity servicio) {
        return dao.add(servicio);
    }

    public Either<ErrorC, Integer> update(ServicioEntity servicio) {
        return dao.update(servicio);
    }

    public Either<ErrorC, Integer> delete(int id) {
        return dao.delete(id);
    }
}
