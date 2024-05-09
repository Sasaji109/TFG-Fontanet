package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOValoraciones;
import com.example.tfgfontanet.data.modelo.ProfesionalMongo;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class ValoracionesService {

    private final DAOValoraciones dao;

    @Inject
    public ValoracionesService(DAOValoraciones dao) {
        this.dao = dao;
    }

    public Either<ErrorC, ProfesionalMongo> getAllByProf(int profesionalId) {
        return dao.getAllByProf(profesionalId);
    }

    public Either<ErrorC, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion) {
        return dao.addValoracion(profesionalId, valoracion);
    }
}
