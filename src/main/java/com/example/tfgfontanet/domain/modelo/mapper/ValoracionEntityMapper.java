package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.data.modelo.ProfesionalMongo;
import com.example.tfgfontanet.domain.modelo.ProfesionalConVal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ValoracionEntityMapper {
    ProfesionalMongo toProfesionalMongo(ProfesionalConVal profesionalConVal);
    ProfesionalConVal toProfesionalConVal(ProfesionalMongo profesionalMongo);
}
