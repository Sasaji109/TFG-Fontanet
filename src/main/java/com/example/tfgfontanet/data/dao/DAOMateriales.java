package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import java.util.List;

public interface DAOMateriales {
    Either<ErrorC, List<MaterialEntity>> getAll();
    Either<ErrorC, MaterialEntity> get(int id);
    Either<ErrorC, Integer> add(MaterialEntity material);
    Either<ErrorC, Integer> update(MaterialEntity material);
    Either<ErrorC, Integer> delete(int id);
}
