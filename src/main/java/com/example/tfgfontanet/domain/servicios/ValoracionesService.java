package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOValoraciones;
import com.example.tfgfontanet.data.modelo.ProfesionalMongo;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import com.example.tfgfontanet.domain.modelo.mapper.ValoracionEntityMapper;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValoracionesService {

    private final DAOValoraciones dao;
    private final ValoracionEntityMapper valoracionEntityMapper;

    public Either<DAOError, ProfesionalMongo> getAllByProf(int profesionalId) {
        return dao.getAllByProf(profesionalId);
    }

    public Either<DAOError, Integer> addValoracion(int profesionalId, ValoracionMongo valoracion) {
        return dao.addValoracion(profesionalId, valoracion);
    }
}
