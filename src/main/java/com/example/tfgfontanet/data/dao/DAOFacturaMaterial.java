package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import io.vavr.control.Either;

public interface DAOFacturaMaterial {
    Either<ErrorC, Integer> addFacturaMaterial(FacturaMaterialEntity facturaMaterial);
}
