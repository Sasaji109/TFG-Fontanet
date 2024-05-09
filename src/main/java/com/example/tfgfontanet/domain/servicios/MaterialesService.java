package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOMateriales;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;
public class MaterialesService {
    private final DAOMateriales dao;

    @Inject
    public MaterialesService(DAOMateriales dao) {
        this.dao = dao;
    }

    public Either<ErrorC, List<MaterialEntity>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, MaterialEntity> getMaterial(int id) {
        return dao.get(id);
    }

    public Either<ErrorC, Integer> addMaterial(MaterialEntity material) {
        return dao.add(material);
    }

    public Either<ErrorC, Integer> updateMaterial(MaterialEntity material) {
        return dao.update(material);
    }

    public Either<ErrorC, Integer> deleteMaterial(int id) {
        return dao.delete(id);
    }
}
