package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.domain.mapper.ProfesionalEntityMapper;
import com.example.tfgfontanet.domain.modelo.Profesional;
import com.example.tfgfontanet.ui.errores.CustomError;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesionalesService {

    private final DAOProfesionales dao;
    private final ProfesionalEntityMapper profesionalEntityMapper;
    private final PasswordEncoder passwordEncoder;

    public Either<ErrorC, List<ProfesionalEntity>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, List<ProfesionalEntity>> getAllByExp(int experiencia) {
        return dao.getAllByExp(experiencia);
    }

    public Either<ErrorC, List<ProfesionalEntity>> getAllByOficio(String oficio) {
        return dao.getAllByOficio(oficio);
    }

    public Either<ErrorC, List<ProfesionalEntity>> getAllByDisp(String disponibilidad) {
        return dao.getAllByDisp(disponibilidad);
    }

    public Either<ErrorC, List<ProfesionalEntity>> getAllByVal(int valoracion) {
        return dao.getAllByVal(valoracion);
    }

    public Either<ErrorC, ProfesionalEntity> get(int id) {
        return dao.get(id);
    }

    public Either<CustomError, Integer> registroProfesional(Profesional profesional) {
         Either<CustomError, Integer> either;

        try {
            ProfesionalEntity profesionalEntity = profesionalEntityMapper.toProfesionalEntity(profesional);
            profesionalEntity.getUsuario().setFechaEnvio(LocalDateTime.now());
            profesionalEntity.getUsuario().setPassword(passwordEncoder.encode(profesionalEntity.getUsuario().getPassword()));
            dao.add(profesionalEntity);
            either = Either.right(1);
        } catch (Exception e) {
            either = Either.left(new CustomError(Constantes.ERROR_REGISTRO, LocalDateTime.now()));
        }

        return either;
    }

    public Either<ErrorC, Integer> update(ProfesionalEntity profesional) {
        return dao.update(profesional);
    }

    public Either<ErrorC, Integer> updateVal(int id, int val) {
        return dao.updateVal(id, val);
    }

    public Either<ErrorC, Integer> delete(int profesionalId) {
        return dao.delete(profesionalId);
    }
}
