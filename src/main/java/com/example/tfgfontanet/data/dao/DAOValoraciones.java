package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.ProfesionalMongo;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import io.vavr.control.Either;

public interface DAOValoraciones {
    Either<DAOError, ProfesionalMongo> getAllByProf(int profesionalId);
    Either<DAOError, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion);
}
