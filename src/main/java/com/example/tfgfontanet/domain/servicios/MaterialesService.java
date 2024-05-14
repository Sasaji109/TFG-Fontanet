package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
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

    public Either<DAOError, List<MaterialEntity>> getAll() {
        return dao.getAll();
    }

    public Either<DAOError, MaterialEntity> getMaterial(int id) {
        return dao.get(id);
    }

    public Either<DAOError, Integer> addMaterial(MaterialEntity material) {
        return dao.add(material);
    }

    public Either<DAOError, Integer> updateMaterial(MaterialEntity material) {
        return dao.update(material);
    }

    public Either<DAOError, Integer> deleteMaterial(int id) {
        return dao.delete(id);
    }
}
