package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOMateriales;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialesService {

    private final DAOMateriales dao;

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
