package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.data.modelo.ValoracionMongo;
import com.example.tfgfontanet.domain.modelo.Valoracion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValoracionEntityMapper {
    ValoracionMongo toValoracionMongo(Valoracion valoracion);
    Valoracion toValoracion(ValoracionMongo valoracionMongo);
}
