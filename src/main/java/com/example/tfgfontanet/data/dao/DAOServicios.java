package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.ServicioEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOServicios {
    Either<CustomError, List<ServicioEntity>> getAll();
    Either<CustomError, ServicioEntity> get(int id);
    Either<CustomError, Integer> add(ServicioEntity servicio);
    Either<CustomError, Integer> update(ServicioEntity servicio);
    Either<CustomError, Integer> delete(int id);
}
