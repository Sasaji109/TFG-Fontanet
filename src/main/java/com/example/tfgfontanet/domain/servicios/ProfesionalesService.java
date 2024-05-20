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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesionalesService {

    private final DAOProfesionales dao;
    private final ProfesionalEntityMapper profesionalEntityMapper;
    private final PasswordEncoder passwordEncoder;

    public Either<DAOError, List<Profesional>> getAll() {
        return dao.getAll().map(profesionalEntityList -> {
            List<Profesional> profesionales = new ArrayList<>();
            for (ProfesionalEntity profesionalEntity : profesionalEntityList) {
                Profesional profesional = profesionalEntityMapper.toProfesional(profesionalEntity);
                profesionales.add(profesional);
            }
            return profesionales;
        });
    }

    public Either<DAOError, List<Profesional>> getAllByExp(int experiencia) {
        return dao.getAllByExp(experiencia).map(profesionalEntityList -> {
            List<Profesional> profesionales = new ArrayList<>();
            for (ProfesionalEntity profesionalEntity : profesionalEntityList) {
                Profesional profesional = profesionalEntityMapper.toProfesional(profesionalEntity);
                profesionales.add(profesional);
            }
            return profesionales;
        });
    }

    public Either<DAOError, List<Profesional>> getAllByOficio(String oficio) {
        return dao.getAllByOficio(oficio).map(profesionalEntityList -> {
            List<Profesional> profesionales = new ArrayList<>();
            for (ProfesionalEntity profesionalEntity : profesionalEntityList) {
                Profesional profesional = profesionalEntityMapper.toProfesional(profesionalEntity);
                profesionales.add(profesional);
            }
            return profesionales;
        });
    }

    public Either<DAOError, List<Profesional>> getAllByDisp(String disponibilidad) {
        return dao.getAllByDisp(disponibilidad).map(profesionalEntityList -> {
            List<Profesional> profesionales = new ArrayList<>();
            for (ProfesionalEntity profesionalEntity : profesionalEntityList) {
                Profesional profesional = profesionalEntityMapper.toProfesional(profesionalEntity);
                profesionales.add(profesional);
            }
            return profesionales;
        });
    }

    public Either<DAOError, List<Profesional>> getAllByVal(int valoracion) {
        return dao.getAllByVal(valoracion).map(profesionalEntityList -> {
            List<Profesional> profesionales = new ArrayList<>();
            for (ProfesionalEntity profesionalEntity : profesionalEntityList) {
                Profesional profesional = profesionalEntityMapper.toProfesional(profesionalEntity);
                profesionales.add(profesional);
            }
            return profesionales;
        });
    }

    public Either<DAOError, Profesional> get(int id) {
        return dao.get(id).map(profesionalEntityMapper::toProfesional);
    }

    public Boolean registroProfesional(Profesional profesional) {
        try {
            ProfesionalEntity profesionalEntity = profesionalEntityMapper.toProfesionalEntity(profesional);
            profesionalEntity.getUsuario().setFechaEnvio(LocalDateTime.now());
            profesionalEntity.getUsuario().setPassword(passwordEncoder.encode(profesionalEntity.getUsuario().getPassword()));
            profesionalEntity.getUsuario().setRole("PROF");
            dao.add(profesionalEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Either<DAOError, Integer> update(Profesional profesional) {
        return dao.update(profesionalEntityMapper.toProfesionalEntity(profesional));
    }

    public Either<DAOError, Integer> updateVal(int id, int val) {
        return dao.updateVal(id, val);
    }

    public Either<DAOError, Integer> delete(int profesionalId) {
        return dao.delete(profesionalId);
    }
}
