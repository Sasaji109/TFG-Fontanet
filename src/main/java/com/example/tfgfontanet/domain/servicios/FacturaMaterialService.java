package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOFacturaMaterial;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacturaMaterialService {

    private final DAOFacturaMaterial dao;

    public Either<DAOError, Integer> addFacturaMaterial(FacturaMaterialEntity facturaMaterial) {
        return dao.addFacturaMaterial(facturaMaterial);
    }
}
