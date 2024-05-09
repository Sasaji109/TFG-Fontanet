package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOProfesionales;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import java.util.List;

public class ProfesionalesService {
    private final DAOProfesionales dao;

    @Inject
    public ProfesionalesService(DAOProfesionales dao) {
        this.dao = dao;
    }

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

    public Either<ErrorC, Integer> add(ProfesionalEntity profesional) {
        return dao.add(profesional);
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
