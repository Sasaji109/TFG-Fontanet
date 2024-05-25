package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.modelo.MaterialEntity;
import com.example.tfgfontanet.domain.modelo.Material;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constantes.SPRING)
public interface MaterialEntityMapper {
    MaterialEntity toMaterialEntity(Material material);
    Material toMaterial(MaterialEntity materialEntity);
}
