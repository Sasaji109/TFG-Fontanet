package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOMateriales {
    Either<DAOError, List<MaterialEntity>> getAll();
    Either<DAOError, MaterialEntity> get(int id);
    Either<DAOError, Integer> add(MaterialEntity material);
    Either<DAOError, Integer> update(MaterialEntity material);
    Either<DAOError, Integer> delete(int id);
}
