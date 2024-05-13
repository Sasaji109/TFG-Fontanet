package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOValoraciones;
import com.example.tfgfontanet.data.modelo.ProfesionalMongo;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValoracionesService {

    private final DAOValoraciones dao;

    public Either<ErrorC, ProfesionalMongo> getAllByProf(int profesionalId) {
        return dao.getAllByProf(profesionalId);
    }

    public Either<ErrorC, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion) {
        return dao.addValoracion(profesionalId, valoracion);
    }
}
