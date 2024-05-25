package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.dao.DAOValoraciones;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.domain.modelo.Valoracion;
import com.example.tfgfontanet.domain.modelo.mapper.ProfesionalEntityMapper;
import com.example.tfgfontanet.domain.modelo.mapper.ValoracionEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ValoracionesService {

    private final DAOValoraciones daoValoraciones;
    private final DAOUsuario daoUsuario;
    private final DAOProfesionales daoProfesionales;
    private final ProfesionalEntityMapper profesionalEntityMapper;
    private final ValoracionEntityMapper valoracionEntityMapper;

    public Either<DAOError, List<Valoracion>> getAllByProf() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
        Profesional profesional = daoProfesionales.getByUserId(usuario.getUserId()).map(profesionalEntityMapper::toProfesional).getOrElseThrow(() -> new NotFoundException(Constantes.PROFESIONAL_NOT_FOUND));

        return daoValoraciones.getAllByProf(profesional.getProfesionalId()).map(valoracionEntityList -> {
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
            daoValoraciones.addValoracion(profesionalId, valoracionEntityMapper.toValoracionMongo(valoracion));
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }
}
