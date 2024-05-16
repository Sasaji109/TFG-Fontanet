package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.data.modelo.ContratoEntity;
import com.example.tfgfontanet.domain.modelo.Contrato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContratoEntityMapper {
    ContratoEntity toContratoEntity(Contrato contrato);
    Contrato toContrato(ContratoEntity contratoEntity);
}
