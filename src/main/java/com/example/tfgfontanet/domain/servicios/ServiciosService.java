package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOServicios;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiciosService {

    private final DAOServicios dao;

    public Either<DAOError, List<ServicioEntity>> getAll() {
        return dao.getAll();
    }

    public Either<DAOError, ServicioEntity> get(int id) {
        return dao.get(id);
    }

    public Either<DAOError, Integer> add(ServicioEntity servicio) {
        return dao.add(servicio);
    }

    public Either<DAOError, Integer> update(ServicioEntity servicio) {
        return dao.update(servicio);
    }

    public Either<DAOError, Integer> delete(int id) {
        return dao.delete(id);
    }
}
