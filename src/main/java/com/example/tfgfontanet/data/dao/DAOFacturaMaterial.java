package com.example.tfgfontanet.data.dao;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import io.vavr.control.Either;

public interface DAOFacturaMaterial {
    Either<DAOError, Integer> addFacturaMaterial(FacturaMaterialEntity facturaMaterial);
}
