package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import com.example.tfgfontanet.domain.modelo.Valoracion;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constantes.SPRING)
public interface ValoracionEntityMapper {
    ValoracionMongo toValoracionMongo(Valoracion valoracion);
    Valoracion toValoracion(ValoracionMongo valoracionMongo);
}
