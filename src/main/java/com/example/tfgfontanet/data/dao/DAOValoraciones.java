package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import io.vavr.control.Either;
import java.util.List;

public interface DAOValoraciones {
    Either<CustomError, List<ValoracionMongo>> getAllByProf(int profesionalId);
    Either<CustomError, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion);
}
