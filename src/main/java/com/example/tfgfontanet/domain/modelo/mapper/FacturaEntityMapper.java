package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.modelo.FacturaEntity;
import com.example.tfgfontanet.domain.modelo.Factura;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constantes.SPRING)
public interface FacturaEntityMapper {
    FacturaEntity toFacturaEntity(Factura factura);
    Factura toFactura(FacturaEntity facturaEntity);
}
