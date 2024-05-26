package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOMateriales {
    Either<CustomError, List<MaterialEntity>> getAll();
    Either<CustomError, MaterialEntity> get(int id);
    Either<CustomError, Integer> add(MaterialEntity material);
    Either<CustomError, Integer> update(MaterialEntity material);
    Either<CustomError, Integer> delete(int id);
}
