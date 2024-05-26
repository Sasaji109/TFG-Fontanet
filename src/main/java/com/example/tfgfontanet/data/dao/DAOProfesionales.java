package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOProfesionales {
    Either<CustomError, List<ProfesionalEntity>> getAll();
    Either<CustomError, List<ProfesionalEntity>> getAllByExp(int experiencia);
    Either<CustomError, List<ProfesionalEntity>> getAllByOficio(String oficio);
    Either<CustomError, List<ProfesionalEntity>> getAllByDisp(String disponibilidad);
    Either<CustomError, List<ProfesionalEntity>> getAllByVal(int valoracion);
    Either<CustomError, ProfesionalEntity> get(int id);
    Either<CustomError, ProfesionalEntity> getByUserId(int userId);
    Either<CustomError, Integer> add(ProfesionalEntity profesional);
    Either<CustomError, Integer> update(ProfesionalEntity profesional);
    Either<CustomError, Integer> updateVal(int id, int val);
    Either<CustomError, Integer> delete(int profesionalId);
}
