package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.data.modelo.FacturaEntity;
import com.example.tfgfontanet.domain.modelo.Factura;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacturaEntityMapper {
    FacturaEntity toFacturaEntity(Factura contrato);
    Factura toFactura(FacturaEntity contratoEntity);
}
