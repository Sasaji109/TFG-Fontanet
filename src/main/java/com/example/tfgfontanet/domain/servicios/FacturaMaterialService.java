package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.data.dao.DAOFacturaMaterial;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class FacturaMaterialService {
    private final DAOFacturaMaterial dao;

    @Inject
    public FacturaMaterialService(DAOFacturaMaterial dao) {
        this.dao = dao;
    }

    public Either<ErrorC, Integer> addFacturaMaterial(FacturaMaterialEntity facturaMaterial) {
        return dao.addFacturaMaterial(facturaMaterial);
    }
}
