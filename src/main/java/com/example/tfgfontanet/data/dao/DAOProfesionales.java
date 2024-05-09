package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOProfesionales {
    Either<ErrorC, List<ProfesionalEntity>> getAll();
    Either<ErrorC, List<ProfesionalEntity>> getAllByExp(int experiencia);
    Either<ErrorC, List<ProfesionalEntity>> getAllByOficio(String oficio);
    Either<ErrorC, List<ProfesionalEntity>> getAllByDisp(String disponibilidad);
    Either<ErrorC, List<ProfesionalEntity>> getAllByVal(int valoracion);
    Either<ErrorC, ProfesionalEntity> get(int id);
    Either<ErrorC, Integer> add(ProfesionalEntity profesional);
    Either<ErrorC, Integer> update(ProfesionalEntity profesional);
    Either<ErrorC, Integer> updateVal(int id, int val);
    Either<ErrorC, Integer> delete(int profesionalId);
}
