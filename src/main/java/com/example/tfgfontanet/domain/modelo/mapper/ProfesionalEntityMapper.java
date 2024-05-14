package com.example.tfgfontanet.domain.modelo.mapper;
import com.example.tfgfontanet.data.modelo.ProfesionalEntity;
import com.example.tfgfontanet.domain.modelo.Profesional;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfesionalEntityMapper {
    ProfesionalEntity toProfesionalEntity(Profesional profesional);
    Profesional toProfesional(ProfesionalEntity profesionalEntity);
}
