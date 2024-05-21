package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import io.vavr.control.Either;
import java.util.List;

public interface DAOValoraciones {
    Either<DAOError, List<ValoracionMongo>> getAllByProf(int profesionalId);
    Either<DAOError, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion);
}
