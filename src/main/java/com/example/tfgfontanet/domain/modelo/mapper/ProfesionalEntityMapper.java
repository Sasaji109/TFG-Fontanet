package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.domain.modelo.Profesional;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constantes.SPRING)
public interface ProfesionalEntityMapper {
    ProfesionalEntity toProfesionalEntity(Profesional profesional);
    Profesional toProfesional(ProfesionalEntity profesionalEntity);
}
