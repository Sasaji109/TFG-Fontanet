package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOProfesionales {
    Either<DAOError, List<ProfesionalEntity>> getAll();
    Either<DAOError, List<ProfesionalEntity>> getAllByExp(int experiencia);
    Either<DAOError, List<ProfesionalEntity>> getAllByOficio(String oficio);
    Either<DAOError, List<ProfesionalEntity>> getAllByDisp(String disponibilidad);
    Either<DAOError, List<ProfesionalEntity>> getAllByVal(int valoracion);
    Either<DAOError, ProfesionalEntity> get(int id);
    Either<DAOError, Integer> add(ProfesionalEntity profesional);
    Either<DAOError, Integer> update(ProfesionalEntity profesional);
    Either<DAOError, Integer> updateVal(int id, int val);
    Either<DAOError, Integer> delete(int profesionalId);
}
