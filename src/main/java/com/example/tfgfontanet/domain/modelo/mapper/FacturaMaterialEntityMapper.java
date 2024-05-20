package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import com.example.tfgfontanet.domain.modelo.FacturaMaterial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacturaMaterialEntityMapper {
    FacturaMaterialEntity toFacturaMaterialEntity(FacturaMaterial facturaMaterial);
    FacturaMaterial toFacturaMaterial(FacturaMaterialEntity facturaMaterial);
}
