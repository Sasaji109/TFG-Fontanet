package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.data.modelo.ServicioEntity;
import com.example.tfgfontanet.domain.modelo.Servicio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicioEntityMapper {
    ServicioEntity toServicioEntity(Servicio servicio);
    Servicio toServicio(ServicioEntity servicioEntity);
}
