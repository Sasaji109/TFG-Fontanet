package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import io.vavr.control.Either;

public interface DAOFacturaMaterial {
    Either<CustomError, Integer> addFacturaMaterial(FacturaMaterialEntity facturaMaterial);
}
