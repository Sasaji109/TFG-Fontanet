package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOServicios {
    Either<DAOError, List<ServicioEntity>> getAll();
    Either<DAOError, ServicioEntity> get(int id);
    Either<DAOError, Integer> add(ServicioEntity servicio);
    Either<DAOError, Integer> update(ServicioEntity servicio);
    Either<DAOError, Integer> delete(int id);
}
