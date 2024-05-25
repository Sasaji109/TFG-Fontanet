package com.example.tfgfontanet.ui.controllers;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.domain.modelo.Factura;
import com.example.tfgfontanet.domain.servicios.FacturasService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FacturasController {

    private final FacturasService facturasService;

    @QueryMapping
    public List<Factura> getAllFacturas() {
        return facturasService.getAll().getOrElseThrow(() -> new NotFoundException(Constantes.FACTURAS_NOT_FOUND));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public List<Factura> getFacturasByCliente() {
        return facturasService.getFacturasByCliente().getOrElseThrow(() -> new NotFoundException(Constantes.FACTURAS_NO_ENCONTRADAS_PARA_EL_CLIENTE));
    }

    @QueryMapping
    @RolesAllowed({Constantes.PROFESIONAL})
    public List<Factura> getFacturasByProfesional() {
        return facturasService.getFacturasByProfesional().getOrElseThrow(() -> new NotFoundException(Constantes.FACTURAS_NO_ENCONTRADAS_PARA_EL_PROFESIONAL));
    }

    @QueryMapping
    @RolesAllowed({Constantes.CLIENTE})
    public Factura getFacturaById(@Argument Integer facturaId) {
        return facturasService.get(facturaId).getOrElseThrow(() -> new NotFoundException(Constantes.FACTURA_NOT_FOUND));
    }

    @PostMapping(Constantes.FACTURA_ADD_PATH)
    @RolesAllowed({Constantes.PROFESIONAL})
    public String addFactura(@RequestBody Factura factura) {
        if (Boolean.TRUE.equals(facturasService.add(factura))) {
            return Constantes.FACTURA_ANADIDA_EXITOSAMENTE;
        } else {
            return Constantes.FACTURA_NO_ANADIDA;
        }
    }

    @PutMapping(Constantes.FACTURA_UPDATE_ESTADO_PATH)
    @RolesAllowed({Constantes.CLIENTE})
    public Integer updateFacturaEstado(@RequestParam(Constantes.FACTURA_ID) Integer facturaId, @RequestParam(Constantes.ESTADO) String estado) {
        return facturasService.updateEstado(facturaId, estado).getOrElseThrow(() -> new CRUDException(Constantes.ESTADO_DE_LA_FACTURA_NO_ACTUALIZADO));
    }
}
