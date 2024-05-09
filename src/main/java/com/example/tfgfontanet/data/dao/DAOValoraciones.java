package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.ProfesionalMongo;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import io.vavr.control.Either;

public interface DAOValoraciones {
    Either<ErrorC, ProfesionalMongo> getAllByProf(int profesionalId);
    Either<ErrorC, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion);
}
