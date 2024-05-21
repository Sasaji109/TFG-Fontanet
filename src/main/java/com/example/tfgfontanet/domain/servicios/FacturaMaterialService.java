package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.data.dao.DAOFacturaMaterial;
import com.example.tfgfontanet.domain.modelo.FacturaMaterial;
import com.example.tfgfontanet.domain.modelo.mapper.FacturaMaterialEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacturaMaterialService {

    private final DAOFacturaMaterial dao;
    private final FacturaMaterialEntityMapper facturaMaterialEntityMapper;

    public Boolean addFacturaMaterial(FacturaMaterial facturaMaterial) {
        try {
            dao.addFacturaMaterial(facturaMaterialEntityMapper.toFacturaMaterialEntity(facturaMaterial));
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }
}
