package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOMateriales;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import com.example.tfgfontanet.domain.modelo.Material;
import com.example.tfgfontanet.domain.modelo.mapper.MaterialEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialesService {

    private final DAOMateriales dao;
    private final MaterialEntityMapper materialEntityMapper;

    public Either<DAOError, List<Material>> getAll() {
        return dao.getAll().map(materialEntityList -> {
            List<Material> materiales = new ArrayList<>();
            for (MaterialEntity materialEntity : materialEntityList) {
                Material material = materialEntityMapper.toMaterial(materialEntity);
                materiales.add(material);
            }
            return materiales;
        });
    }

    public Either<DAOError, Material> get(int id) {
        return dao.get(id).map(materialEntityMapper::toMaterial);
    }

    public Boolean addMaterial(Material material) {
        try {
            dao.add(materialEntityMapper.toMaterialEntity(material));
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<DAOError, Integer> updateMaterial(Material material) {
        MaterialEntity materialEntity = materialEntityMapper.toMaterialEntity(material);
        return dao.update(materialEntity);
    }

    public Either<DAOError, Integer> deleteMaterial(int id) {
        return dao.delete(id);
    }
}
