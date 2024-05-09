package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOServicios {
    Either<ErrorC, List<ServicioEntity>> getAll();
    Either<ErrorC, ServicioEntity> get(int id);
    Either<ErrorC, Integer> add(ServicioEntity servicio);
    Either<ErrorC, Integer> update(ServicioEntity servicio);
    Either<ErrorC, Integer> delete(int id);
}
