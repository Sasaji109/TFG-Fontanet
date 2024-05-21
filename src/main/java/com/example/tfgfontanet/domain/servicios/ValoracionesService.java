package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOValoraciones;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import com.example.tfgfontanet.domain.modelo.Valoracion;
import com.example.tfgfontanet.domain.modelo.mapper.ValoracionEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValoracionesService {

    private final DAOValoraciones dao;
    private final ValoracionEntityMapper valoracionEntityMapper;

    public Either<DAOError, List<Valoracion>> getAllByProf(Integer profesionalId) {
        return dao.getAllByProf(profesionalId).map(valoracionEntityList -> {
            List<Valoracion> valoraciones = new ArrayList<>();
            for (ValoracionMongo valoracionMongo : valoracionEntityList) {
                Valoracion valoracion = valoracionEntityMapper.toValoracion(valoracionMongo);
                valoraciones.add(valoracion);
            }
            return valoraciones;
        });
    }

    public Boolean addValoracion(int profesionalId, Valoracion valoracion) {
        try {
            dao.addValoracion(profesionalId, valoracionEntityMapper.toValoracionMongo(valoracion));
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }
}
