package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.domain.modelo.mapper.ProfesionalEntityMapper;
import com.example.tfgfontanet.domain.modelo.Profesional;
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

    public Either<DAOError, List<ProfesionalEntity>> getAll() {
        return dao.getAll();
    }

    public Either<DAOError, List<ProfesionalEntity>> getAllByExp(int experiencia) {
        return dao.getAllByExp(experiencia);
    }

    public Either<DAOError, List<ProfesionalEntity>> getAllByOficio(String oficio) {
        return dao.getAllByOficio(oficio);
    }

    public Either<DAOError, List<ProfesionalEntity>> getAllByDisp(String disponibilidad) {
        return dao.getAllByDisp(disponibilidad);
    }

    public Either<DAOError, List<ProfesionalEntity>> getAllByVal(int valoracion) {
        return dao.getAllByVal(valoracion);
    }

    public Either<DAOError, ProfesionalEntity> get(int id) {
        return dao.get(id);
    }

    public Boolean registroProfesional(Profesional profesional) {
        try {
            ProfesionalEntity profesionalEntity = profesionalEntityMapper.toProfesionalEntity(profesional);
            profesionalEntity.getUsuario().setFechaEnvio(LocalDateTime.now());
            profesionalEntity.getUsuario().setPassword(passwordEncoder.encode(profesionalEntity.getUsuario().getPassword()));
            dao.add(profesionalEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Either<DAOError, Integer> update(ProfesionalEntity profesional) {
        return dao.update(profesional);
    }

    public Either<DAOError, Integer> updateVal(int id, int val) {
        return dao.updateVal(id, val);
    }

    public Either<DAOError, Integer> delete(int profesionalId) {
        return dao.delete(profesionalId);
    }
}
