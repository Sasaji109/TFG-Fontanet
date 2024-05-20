package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOFacturaMaterial;
import com.example.tfgfontanet.data.modelo.FacturaMaterialEntity;
import com.example.tfgfontanet.domain.modelo.mapper.FacturaMaterialEntityMapper;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacturaMaterialService {

    private final DAOFacturaMaterial dao;
    private final FacturaMaterialEntityMapper facturaMaterialEntityMapper;

    public Either<DAOError, Integer> addFacturaMaterial(FacturaMaterialEntity facturaMaterial) {
        return dao.addFacturaMaterial(facturaMaterial);
    }
}
